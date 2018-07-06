package ru.sbtqa.patterns.ui.pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.patterns.ui.pageobject.elements.MenuBar;
import ru.sbtqa.patterns.ui.pageobject.elements.SearchResult;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.PageFactory.initElements;

public class SearchResultsPage {

    private WebDriver driver;

    @FindBy(xpath = "//div[contains(@class,'top_nav')]")
    private MenuBar menuBar;

    @FindBy(xpath = "//ul[contains(@aria-label,'Результаты поиска')]/li")
    private List<SearchResult> results;

    public SearchResultsPage(WebDriver driver) {
        this.driver = driver;
        initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void checkResultTitle(int position, String expected) {
        assertEquals(expected, results.get(--position).getTitle());
    }

    public void selectSearchResultByOrder(int order) {
        results.get(--order).select();
        driver.getWindowHandles()
                .forEach((windowHandle) -> driver.switchTo().window(windowHandle));
    }
}
