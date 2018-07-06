package ru.sbtqa.tutorials.xunit.yandexmatchers.core.pages;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class GoogleSearchWidget {

    @FindBy(name = "q")
    WebElement searchBox;

    @FindBy(xpath = "//input[contains(@value, 'Поиск')]")
    WebElement searchButton;

    @Inject
    public GoogleSearchWidget(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void searchFor(String txt) {
        searchBox.sendKeys(txt);
        searchButton.click();
    }
}