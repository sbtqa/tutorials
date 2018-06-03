package ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Point;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.yandex.qatools.htmlelements.annotations.Name;
import ru.yandex.qatools.htmlelements.element.Button;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TextInput;

@Name("Параметры поиска")
@FindBy(xpath = "//div[@class='n-filter-panel-aside__content']")
public class SearchParamsBlock extends HtmlElement {

    private WebDriverWait wait = new WebDriverWait(PageFactory.getWebDriver(), 10);

    @Name("Цена от")
    @FindBy(xpath = "//input[@id='glf-pricefrom-var']")
    public TextInput price_from;

    @Name("Цена до")
    @FindBy(xpath = "//input[@id='glf-priceto-var']")
    public TextInput price_till;

    @Name("Применить")
    @FindBy(xpath = "//span[@class='button__text' and text()='Применить']//ancestor::button")
    public Button apply;

    @ActionTitle("задает цену от")
    public void setPrice_from(String price){
        wait.until(ExpectedConditions.elementToBeClickable(price_from));
        price_from.clear();
        if (check_price(price)) price_from.sendKeys(price);
    }

    @ActionTitle("задает цену до")
    public void setPrice_till(String price){
        wait.until(ExpectedConditions.elementToBeClickable(price_till));
        price_till.clear();
        if (check_price(price)) price_till.sendKeys(price);
    }

    private boolean check_price(String price){
        try {
            Integer.parseInt(price);
        } catch (NumberFormatException e) {
            e.printStackTrace();
            Assert.fail("Неверный параметр: " + price);
            return false;
        }
        return true;
    }

    @ActionTitle("выбирает производителя")
    public void selectVendors(String params){
        String[] vendors = params.split(",");
        for (String vendor: vendors){
            checkVendor(vendor);
        }
    }

    private void checkVendor(String vendor) {
        WebElement searchbar = null, vendor_label = null;
        try {
            PageFactory.getDriver().findElement(By.xpath("//div[@data-filter-id='7893318']/div[2]/div/span/span/input")); //check if search bar is open
            checkVendorInSearchBar(vendor);
        } catch (Exception e) {
            checkVendorInShortList(vendor);
        }
    }

    private void checkVendorInShortList(String vendor){
        try {
            wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//label[@class='checkbox__label' and text()='" + vendor + "']")));
            WebElement vendor_label = getWrappedElement().findElement(By.xpath("//label[@class='checkbox__label' and text()='" + vendor + "']"));
            vendor_label.click();
        } catch (Exception e) {
            //e.printStackTrace();
            getWrappedElement().findElement(By.xpath("//span[@class='button__text' and text()='Показать всё']//ancestor::button")).click();
            checkVendorInSearchBar(vendor);
        }
    }

    private void checkVendorInSearchBar(String vendor){
        WebElement vendor_label=null;
        try {

            String vendor_label_xpath = "//label[@class='checkbox__label' and text()='" + vendor + "']";

            //check if checkbox is enabled
            if (PageFactory.getDriver().findElement(By.xpath(vendor_label_xpath + "//parent::span//parent::div")).getAttribute("class").contains("n-filter-block__item_disabled_yes")) {
                System.out.println("Не найдены товары произодителя '"+vendor+"' с указанными параметрами");
                return;
            }

            vendor_label = PageFactory.getDriver().findElement(By.xpath(vendor_label_xpath));

            //scroll to vendor element
            JavascriptExecutor je = (JavascriptExecutor) PageFactory.getDriver();
            je.executeScript("document.getElementsByClassName('n-filter-block__list-items n-filter-block__list-items_scroll_yes i-bem')[0].scrollTop = "+getScrollOffset(vendor_label));

            vendor_label.click();

        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail("Производитель " + vendor + " не найден");
        }
    }

    private int getScrollOffset(WebElement vendor){
        Point start = PageFactory.getDriver().findElement(By.xpath("//div[@class='n-filter-block__list_title' and text()='A']")).getLocation();
        Point search = vendor.getLocation();
        return search.y-start.y;
    }

    @ActionTitle("нажимает кнопку")
    public void clickApply(String param){
        switch (param){
            case "Применить":
                wait.until(ExpectedConditions.elementToBeClickable(apply));
                apply.click();
                break;
            default:
                Assert.fail("Не найдена кнопка " + param);
                break;
        }

    }
}
