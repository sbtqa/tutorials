package ru.sbt.qa.uitest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.sbt.qa.uitest.elements.SearchResultBox;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class GoogleSearchResultFluentPage {

    @FindBy(xpath = "//div[@class='rc']")
    private List<SearchResultBox> searchResultBoxList;

    public GoogleSearchResultFluentPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public GoogleSearchResultFluentPage checkFirstSearchResult(String expectedLabel, String expectedUrl) {
        SearchResultBox firstSearchResultBox = searchResultBoxList.get(0);
        String actualLabel = firstSearchResultBox.getLabel();
        String actualUrl = firstSearchResultBox.getUrl();
        assertAll("Ожидаемый результат поиска не соответствует действительному!",() -> assertEquals(expectedLabel, actualLabel),
                () -> assertEquals(expectedUrl, actualUrl));
        return this;
    }

    public GoogleSearchResultFluentPage printFirstSearchResult() {
        SearchResultBox firstSearchResultBox = searchResultBoxList.get(0);
        System.out.println(firstSearchResultBox.getUrl());
        System.out.println(firstSearchResultBox.getLabel());
        return this;
    }
}
