package ru.sbtqa.tutorials.xunit.yandexmatchers.core.pages;

import com.google.inject.Inject;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class Google {

    @Inject
    private WebDriver driver;

    @Inject
    private GoogleSearchWidget searchBox;

    @Inject
    private GoogleSearchResult searchResult;

    @Inject
    private Actions actions;

    @Inject
    private JavascriptExecutor jsExecutor;

    @Inject
    public Google(WebDriver driver) {
        this.driver = driver;
    }

    public void goTo() {
        this.driver.get("http://www.google.ru/");
    }

    public GoogleSearchWidget getSearchWidget() {
        return searchBox;
    }

    public GoogleSearchResult getGoogleSearchResult() {
        return searchResult;
    }

    public Object execute(String script) {
        return jsExecutor.executeScript(script);
    }

    public void close() {
        if (driver != null) {
            driver.quit();
        }
    }
}