package ru.sbtqa.tutorials.xunit.yandexmatchers;

import com.google.inject.Inject;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;
import ru.sbtqa.tutorials.xunit.yandexmatchers.core.modules.DriverModule;
import ru.sbtqa.tutorials.xunit.yandexmatchers.core.pages.Google;


/**
 * Примеры использования стандартных матчеров библиотеки Yandex (Webdriver Matchers)
 */
@Guice(modules = {DriverModule.class})
public class WebDriverMatcherTest {

    @Inject
    private Google google;

    @Inject
    private WebDriver driver;

    @Test
    public void shouldDisplayedGooglePageWithResult() {
        google.goTo();
        google.getSearchWidget().searchFor("Sberbank - Russian Commercial Bank");
        google.getResults().displayResult();
    }

    @AfterMethod
    public void teardown() {
        if (driver != null) {
            driver.quit();
        }
    }
}


