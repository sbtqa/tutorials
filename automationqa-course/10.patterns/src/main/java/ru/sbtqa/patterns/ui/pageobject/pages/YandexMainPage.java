package ru.sbtqa.patterns.ui.pageobject.pages;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import static org.openqa.selenium.support.PageFactory.initElements;

public class YandexMainPage {

    @FindBy(id = "text")
    private WebElement search;

    public YandexMainPage(WebDriver driver) {
        driver.get("https://www.yandex.ru");
        initElements(driver, this);
    }

    public void search(String param) {
        search.sendKeys(param);
        search.sendKeys(Keys.ENTER);
    }
}
