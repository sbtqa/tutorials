package ru.sbt.qa.uitest;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import ru.sbt.qa.uitest.pages.GoogleMainPage;
import ru.sbt.qa.uitest.pages.GoogleSearchResultPage;

public class GooglePageFactoryTest {
    private WebDriver driver;

    @BeforeAll
    public static void checkBrowserShim() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void setUp() {
        driver = new ChromeDriver();
        driver.get("https://google.com");
    }

    @AfterEach
    public void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

    @Test
    public void pageFactoryTest() {
        GoogleMainPage googleMainPage = new GoogleMainPage(driver);
        googleMainPage.search("Сбербанк");
        GoogleSearchResultPage googleSearchResultPage = new GoogleSearchResultPage(driver);
        googleSearchResultPage.checkFirstSearchResult("«Сбербанк» - Частным клиентам", "www.sberbank.ru/ru/person");
    }
}
