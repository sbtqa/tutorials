package ru.sbtqa.patterns.extra.keyword.pages.Yandex;

import org.openqa.selenium.By;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.patterns.extra.keyword.elements.YandexMarket.HeaderBlock;
import ru.sbtqa.patterns.extra.keyword.elements.YandexMarket.ProductCard;
import ru.sbtqa.patterns.extra.keyword.elements.YandexMarket.SearchParamsBlock;
import ru.sbtqa.tag.datajack.Stash;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.annotations.ActionTitle;
import ru.sbtqa.tag.pagefactory.annotations.ElementTitle;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

import static java.lang.Integer.parseInt;
import static org.junit.Assert.assertEquals;
import static ru.sbtqa.tag.pagefactory.PageFactory.initElements;

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
        initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(PageFactory.getDriver())), this);
    }

    @ActionTitle("проверяет количество элементов")
    public void checkProductNumber(String number) {
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//div[contains(@class,'preloadable__preloader')]")));
        assertEquals("Количество результатов поиска",
                productCards.size(), parseInt(number));
    }

    @ActionTitle("запоминает элемент в списке под номером")
    public void saveFirstTitle(String number) {
        wait.until(ExpectedConditions.attributeToBe(By.xpath("//div[contains(@class,'n-filter-applied-results__content')]"), "style", "height: auto;"));
        Stash.put("title", productCards.get(parseInt(number) - 1).getProductName());
    }

    @ActionTitle("выполняет поиск с запомненным значением")
    public void searchSavedTitle() {
        this.headerBlock.search(Stash.getValue("title"));
    }
}
