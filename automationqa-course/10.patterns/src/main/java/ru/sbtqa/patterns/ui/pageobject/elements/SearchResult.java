package ru.sbtqa.patterns.ui.pageobject.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.TypifiedElement;

import java.util.List;
import java.util.stream.Collectors;

public class SearchResult extends HtmlElement {
    @FindBy(className = "needsclick")
    private List<WebElement> title;

    @FindBy(xpath = ".//div[contains(@class,'organic__subtitle')]//a")
    private WebElement link;

    public String getTitle() {
        return title.stream().map(WebElement::getText).collect(Collectors.joining(" "));
    }

    public WebElement getLink() {
        return link;
    }
}
