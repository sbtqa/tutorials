package ru.sbtqa.tutorials.xunit.yandexmatchers.core.pages;

import com.google.inject.Inject;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class GoogleSearchResult {

    @FindBy(className = "rc")
    private List<WebElement> results;

    @Inject
    private WebDriverWait driverWait;

    @Inject
    public GoogleSearchResult(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public int getCount() {
        return results.size();
    }

    public void displayResult() {
        driverWait.until(ExpectedConditions
                .visibilityOfElementLocated(By.className("rc")));
        results.stream()
                .map(WebElement::getText)
                .forEach(System.out::println);
    }

    public List<WebElement> getResults() {
        return results;
    }
}