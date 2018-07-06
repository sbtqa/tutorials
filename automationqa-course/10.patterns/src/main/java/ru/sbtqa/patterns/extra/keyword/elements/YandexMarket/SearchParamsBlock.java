package ru.sbtqa.patterns.extra.keyword.elements.YandexMarket;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

import java.util.stream.Stream;

import static java.lang.Integer.parseInt;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

@Name("Параметры поиска")
@FindBy(className = "search-layout")
public class SearchParamsBlock extends HtmlElement {

    private WebDriverWait wait = new WebDriverWait(PageFactory.getWebDriver(), 10);

    @Name("Цена от")
    @FindBy(name = "Цена от")
    private TextInput price_from;

    @Name("Цена до")
    @FindBy(name = "Цена до")
    public TextInput price_till;

    @Name("Применить")
    @FindBy(xpath = "//span[@class='button__text' and text()='Применить']//ancestor::button")
    private Button apply;

    @ActionTitle("задает цену от")
    public void setPrice_from(String price) {
        wait.until(visibilityOf(price_from));
        price_from.clear();
        if (check_price(price)) price_from.sendKeys(price);
    }

    @ActionTitle("задает цену до")
    public void setPrice_till(String price) {
        wait.until(visibilityOf(price_till));
        price_till.clear();
        if (check_price(price)) price_till.sendKeys(price);
    }

    private boolean check_price(String price) {
        return parseInt(price) > 0;
    }

    @ActionTitle("выбирает производителя")
    public void selectVendors(String params) {
        Stream.of(params.trim().split(",")).forEach(this::checkVendor);
    }

    private void checkVendor(String vendor) {
        try {
            PageFactory.getDriver().findElement(By.name("Поле поиска"));
            checkVendorInSearchBar(vendor);
        } catch (Exception e) {
            checkVendorInShortList(vendor);
        }
    }

    private void checkVendorInShortList(String vendor) {
        try {
            WebElement vendor_label = getWrappedElement().findElement(By.xpath("//span[text()='" + vendor + "']"));
            vendor_label.click();
        } catch (Exception e) {
            findElement(By.xpath("//a[text()='Показать всё']")).click();
            checkVendorInSearchBar(vendor);
        }
    }

    private void checkVendorInSearchBar(String vendor) {
        final String vendorXpath = "//input[@name='Производитель " + vendor + "']/ancestor::label";
        wait.until(visibilityOfElementLocated(By.xpath(vendorXpath)));
        WebElement vendor_label = findElement(By.xpath(vendorXpath));

        //scroll to vendor element
        JavascriptExecutor je = (JavascriptExecutor) PageFactory.getDriver();
        je.executeScript("document.getElementsByClassName('_1DqdIFuq1G')[0].scrollTop = " + getScrollOffset(vendor_label));

        vendor_label.click();
    }

    private int getScrollOffset(WebElement vendor) {
        Point start = findElement(By.xpath("//div[text()='A']")).getLocation();
        Point search = vendor.getLocation();
        return search.y - start.y;
    }
}
