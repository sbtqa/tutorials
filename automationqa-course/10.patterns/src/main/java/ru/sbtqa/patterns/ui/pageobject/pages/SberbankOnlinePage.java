package ru.sbtqa.patterns.ui.pageobject.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.PageFactory.initElements;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

public class SberbankOnlinePage {
    @FindBy(id = "login")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//div[contains(@id,'buttonSubmit')]//button")
    private WebElement submit;

    @FindBy(xpath = "//div[contains(@id,'AccessDenied')]//h2")
    private WebElement accessDeniedTitle;

    private WebDriverWait wait;

    public SberbankOnlinePage(WebDriver driver) {
        initElements(driver, this);
        wait = new WebDriverWait(driver, 10);
    }

    public void login(String login, String password) {
        wait.until(visibilityOf(this.login));
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        this.submit.click();
    }

    public void checkLoginFail() {
        wait.until(visibilityOf(accessDeniedTitle));
        assertEquals("Во входе отказано", accessDeniedTitle.getText());
    }
}
