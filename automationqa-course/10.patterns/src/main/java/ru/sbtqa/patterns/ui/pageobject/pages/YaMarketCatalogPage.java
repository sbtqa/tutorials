package ru.sbtqa.patterns.ui.pageobject.pages.yandexmarket;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket.HeaderBlock;
import ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket.ProductCard;
import ru.sbtqa.patterns.ui.pageobject.elements.YandexMarket.SearchParamsBlock;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

@PageEntry(title = "Каталог товаров")
public class YaMarketCatalogPage extends Page {

    private WebDriverWait wait = new WebDriverWait(PageFactory.getWebDriver(), 20);

    @ElementTitle("Список товаров")
    @FindBy(xpath = ".//div[contains(@class,'n-snippet-card2 i-bem b-zone')]")
    private List<ProductCard> productCards;

    @ElementTitle("Параметры поиска")
    private SearchParamsBlock searchParamsBlock;

    private HeaderBlock headerBlock;

    public YaMarketCatalogPage() {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'preloadable__preloader')]")));
        PageFactory.initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

    @ActionTitle("проверяет количество элементов")
    public void checkProductNumber(String number){
        int num = Integer.parseInt(number);
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'preloadable__preloader')]")));
        if (productCards.size()!=num) {
            try {
                Assert.fail("Количество элементов на странице  "+ productCards.size() + "!=" + number);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    @ActionTitle("запоминает элемент в списке под номером")
    public void saveFirstTitle(String number){
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[contains(@class,'n-filter-applied-results__content')]"),"style","height: auto;"));
        HeaderBlock.setSavedTitle(productCards.get(Integer.parseInt(number)-1).getProductName());
    }

    @ActionTitle("выполняет поиск с запомненным значением")
    public void searchSavedTitle(){
        this.headerBlock.searchSavedTitle();
    }
}
