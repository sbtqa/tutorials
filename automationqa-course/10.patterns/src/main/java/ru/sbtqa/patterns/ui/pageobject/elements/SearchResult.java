package ru.sbtqa.patterns.ui.pageobject.elements;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;
import ru.yandex.qatools.htmlelements.element.Link;
import ru.yandex.qatools.htmlelements.element.TextBlock;

import java.util.List;

import static java.util.stream.Collectors.joining;

public class SearchResult extends HtmlElement {
    @FindBy(className = "needsclick")
    private List<WebElement> title;

    @FindBy(xpath = ".//div[contains(@class,'organic__subtitle')]//a")
    private Link link;

    @FindBy(xpath = ".//div[contains(@class,'organic__content-wrapper')]")
    private TextBlock description;

    public String getTitle() {
        return title.stream().map(WebElement::getText).collect(joining(" "));
    }

    public WebElement getDescription() {
        return description;
    }

    public void select() {
        link.click();
    }
}
