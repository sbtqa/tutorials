package ru.sbtqa.patterns.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.patterns.ui.pageobject.pages.SberbankOnlinePage;
import ru.sbtqa.patterns.ui.pageobject.pages.SearchResultsPage;
import ru.sbtqa.patterns.ui.pageobject.pages.YandexMainPage;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;

class PageObjectTest {

    private WebDriver driver;
    private WebDriverWait wait;

    static {
        System.setProperty("webdriver.chrome.driver", "src/test/resources/webdrivers/chromedriver.exe");
    }

    @BeforeEach
    private void initDriver() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        wait = new WebDriverWait(driver, 10);
    }

    @AfterEach
    private void closeDriver() {
        driver.quit();
    }

    /**
     * Hardcoded test
     */
    @Test
    void noPageObjectTest() {
        driver.get("https://www.yandex.ru");
        WebElement search = driver.findElement(By.id("text"));
        search.sendKeys("Сбербанк Онлайн");
        search.sendKeys(Keys.ENTER);

        List<WebElement> results = driver.findElements(By.xpath("//ul[contains(@aria-label,'Результаты поиска')]/li"));
        wait.until(visibilityOf(driver.findElement(By.className("needsclick"))));
        assertEquals("Сбербанк Онлайн", results.get(0).findElements(By.className("needsclick"))
                .stream().map(WebElement::getText).collect(Collectors.joining(" ")));
        results.get(0).findElement(By.className("needsclick")).click();

        driver.getWindowHandles()
                .forEach((windowHandle) -> driver.switchTo().window(windowHandle));

        wait.until(visibilityOf(driver.findElement(By.id("login"))));
        driver.findElement(By.id("login")).sendKeys("blahblah");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//div[contains(@id,'buttonSubmit')]//button")).click();

        wait.until(visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'AccessDenied')]"))));
        assertEquals("Во входе отказано", driver.findElement(By.xpath("//div[contains(@id,'AccessDenied')]//h2")).getText());
    }

    @Test
    void pageObjectTest() {
        YandexMainPage yandexMainPage = new YandexMainPage(driver);
        yandexMainPage.search("Сбербанк Онлайн");

        SearchResultsPage searchResultsPage = new SearchResultsPage(driver);
        searchResultsPage.checkResultTitle(1, "Сбербанк Онлайн");
        searchResultsPage.selectSearchResultByOrder(1);

        SberbankOnlinePage sberbankOnlinePage = new SberbankOnlinePage(driver);
        sberbankOnlinePage.login("blahblah", "password");
        sberbankOnlinePage.checkLoginFail();
    }
}
