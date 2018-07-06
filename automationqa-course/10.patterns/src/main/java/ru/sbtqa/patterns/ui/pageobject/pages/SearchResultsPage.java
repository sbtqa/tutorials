package ru.sbtqa.patterns.ui.pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import ru.sbtqa.patterns.ui.pageobject.elements.MenuBar;
import ru.sbtqa.patterns.ui.pageobject.elements.SearchResult;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static ru.sbtqa.tag.pagefactory.PageFactory.getDriver;
import static ru.sbtqa.tag.pagefactory.PageFactory.initElements;

@PageEntry(title = "Результаты поиска")
public class SearchResultsPage extends Page {

    @FindBy(xpath = "//div[contains(@class,'top_nav')]")
    private MenuBar menuBar;

    @FindBy(xpath = "//ul[contains(@aria-label,'Результаты поиска')]/li")
    private List<SearchResult> results;

    public SearchResultsPage() {
        initElements(
                new HtmlElementDecorator(new HtmlElementLocatorFactory(getDriver())), this);
    }

    public List<SearchResult> getResults() {
        return results;
    }

    public void checkResultTitle(int position, String expected) {
        assertEquals(expected, results.get(position).getTitle());

    }

    public void selectSearchResultByOrder(int order, WebDriver driver) {
        results.get(order).getLink().click();
        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
    }
}
