package ru.sbtqa.patterns.behavioural.strategy;

import org.openqa.selenium.WebDriver;

import java.util.function.Consumer;

/**
 * @author Alexey Rumyantsev
 */
public class LoginPage {
    private WebDriver driver;

    public LoginPage(WebDriver webDriver) {
        driver = webDriver;
    }

    public void login(Consumer<WebDriver> consumer) {
        consumer.accept(driver);
    }
}
