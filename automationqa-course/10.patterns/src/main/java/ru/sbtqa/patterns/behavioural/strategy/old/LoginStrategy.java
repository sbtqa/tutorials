package ru.sbtqa.patterns.behavioural.strategy.old;

import org.openqa.selenium.WebDriver;

/**
 * @author Alexey Rumyantsev
 */
public interface LoginStrategy {
    void login(WebDriver driver);
}
