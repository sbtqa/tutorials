package ru.sbtqa.patterns.ui.pageobject.pages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.tag.pagefactory.Page;
import ru.sbtqa.tag.pagefactory.annotations.PageEntry;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static ru.sbtqa.tag.pagefactory.PageFactory.getDriver;
import static ru.sbtqa.tag.pagefactory.PageFactory.initElements;

@PageEntry(title = "Сбербанк Онлайн")
public class SberbankOnlinePage extends Page {
    @FindBy(id = "login")
    private WebElement login;

    @FindBy(id = "password")
    private WebElement password;

    @FindBy(xpath = "//div[contains(@id,'buttonSubmit')]//button")
    private WebElement submit;

    @FindBy(xpath = "//div[contains(@id,'AccessDenied')]//h2")
    private WebElement accessDeniedTitle;

    public SberbankOnlinePage() {
        initElements(getDriver(), this);
    }

    public void login(String login, String password) {
        this.login.sendKeys(login);
        this.password.sendKeys(password);
        this.submit.click();

        new WebDriverWait(getDriver(), 10)
                .until(visibilityOf(accessDeniedTitle));
        assertEquals("Во входе отказано", accessDeniedTitle.getText());
    }
}
