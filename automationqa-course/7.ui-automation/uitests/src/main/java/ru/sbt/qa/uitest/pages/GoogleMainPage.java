package ru.sbt.qa.uitest.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import ru.yandex.qatools.htmlelements.element.TextInput;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementDecorator;
import ru.yandex.qatools.htmlelements.loader.decorator.HtmlElementLocatorFactory;

public class GoogleMainPage {

    @FindBy(xpath = "//input[@id='lst-ib']")
    private TextInput searchInput;

    public GoogleMainPage(WebDriver driver) {
        PageFactory.initElements(new HtmlElementDecorator(new HtmlElementLocatorFactory(driver)), this);
    }

    public void search(String searchingText) {
        searchInput.sendKeys(searchingText);
        searchInput.submit();
    }
}
