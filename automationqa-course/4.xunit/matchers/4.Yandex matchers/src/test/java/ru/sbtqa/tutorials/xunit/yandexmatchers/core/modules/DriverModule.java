package ru.sbtqa.tutorials.xunit.yandexmatchers.core.modules;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Scopes;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class DriverModule extends AbstractModule {

    @Override
    protected void configure() {
        WebDriverManager.chromedriver().setup();
        bind(WebDriver.class)
                .to(ChromeDriver.class)
                .in(Scopes.SINGLETON);
    }

    @Provides
    public Actions getActions(WebDriver driver) {
        return new Actions(driver);
    }

    @Provides
    public JavascriptExecutor getExecutor(WebDriver driver) {
        return (JavascriptExecutor)(driver);
    }

    @Provides
    public WebDriverWait getWebDriverWait(WebDriver driver) {
        return new WebDriverWait(driver, 5);
    }
}