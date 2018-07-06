package ru.sbtqa.patterns.ui;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.sbtqa.patterns.ui.pageobject.pages.SberbankOnlinePage;
import ru.sbtqa.patterns.ui.pageobject.pages.SearchResultsPage;
import ru.sbtqa.patterns.ui.pageobject.pages.YandexMainPage;
import ru.sbtqa.tag.pagefactory.PageFactory;
import ru.sbtqa.tag.pagefactory.exceptions.PageInitializationException;

import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOf;
import static ru.sbtqa.tag.pagefactory.PageFactory.getInstance;

public class PageObjectTest {

    private WebDriver driver;

    @BeforeEach
    private void initDriver() {
        driver=PageFactory.getDriver();
    }

    @AfterEach
    private void closeDriver() {
        PageFactory.dispose();
    }

    /**
    * Hardcoded test
     * */
    @Test
    public void noPageObjectTest() {
        WebElement search = driver.findElement(By.id("text"));
        search.sendKeys("Сбербанк Онлайн");
        search.sendKeys(Keys.ENTER);

        List<WebElement> results = driver.findElements(By.xpath("//ul[contains(@aria-label,'Результаты поиска')]/li"));
        new WebDriverWait(driver, 10)
                .until(visibilityOf(driver.findElement(By.className("needsclick"))));
        assertEquals("Сбербанк Онлайн", results.get(0).findElements(By.className("needsclick"))
                .stream().map(WebElement::getText).collect(Collectors.joining(" ")));
        results.get(0).findElement(By.className("needsclick")).click();

        driver.switchTo().window(driver.getWindowHandles().toArray()[1].toString());
        driver.findElement(By.id("login")).sendKeys("blahblah");
        driver.findElement(By.id("password")).sendKeys("password");
        driver.findElement(By.xpath("//div[contains(@id,'buttonSubmit')]//button")).click();

        new WebDriverWait(driver, 10)
                .until(visibilityOf(driver.findElement(By.xpath("//div[contains(@id,'AccessDenied')]"))));
        assertEquals("Во входе отказано", driver.findElement(By.xpath("//div[contains(@id,'AccessDenied')]//h2")).getText());
    }

    @Test
    public void pageObjectTest() throws PageInitializationException {
        YandexMainPage yandexMainPage = (YandexMainPage) getInstance().getPage("Яндекс");
        yandexMainPage.search("Сбербанк Онлайн");

        SearchResultsPage searchResultsPage = (SearchResultsPage) getInstance().getPage(SearchResultsPage.class);
        searchResultsPage.checkResultTitle(0, "Сбербанк Онлайн");
        searchResultsPage.selectSearchResultByOrder(0, driver);

        SberbankOnlinePage sberbankOnlinePage = (SberbankOnlinePage) getInstance().getPage(SberbankOnlinePage.class);
        sberbankOnlinePage.login("blahblah","password");
    }
}
