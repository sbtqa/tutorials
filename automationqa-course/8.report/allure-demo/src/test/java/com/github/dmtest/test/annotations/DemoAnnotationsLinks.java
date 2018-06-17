package com.github.dmtest.test.annotations;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 4.4 В классе демонстрируется использование аннотаций {@link Link}, {@link Links}, {@link Issue}, {@link Issues},
 * {@link TmsLink}, {@link TmsLinks}.
 */
public class DemoAnnotationsLinks {
    private WebDriver driver;

    @BeforeMethod(description = "Старт драйвера")
    private void openPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "Стоп драйвера")
    private void quitDriver() {
        driver.quit();
    }

    @Links({@Link(name = "«Сбербанк» - Частным клиентам", url = "http://www.sberbank.ru"),
            @Link(name = "Сбербанк Онлайн", url = "https://online.sberbank.ru")})
    @Test(description = "Демонстрация использования аннотаций @Link и @Links")
    public void demoAnnotationLinksTest() {
        driver.get("http://www.sberbank.ru");
        Assert.assertEquals(driver.getTitle(), "«Сбербанк» - Частным клиентам");
        driver.get("https://online.sberbank.ru");
        Assert.assertEquals(driver.getTitle(), "Сбербанк Онлайн");
    }

    @Issues({@Issue("FGY-1"), @Issue("FGY-2")})
    @Test(description = "Демонстрация использования аннотаций @Issue и @Issues")
    public void demoAnnotationIssuesTest() {
        driver.get("invalid_url.ru");
    }

    @TmsLinks({@TmsLink("TL-135"), @TmsLink("TL-158")})
    @Test(description = "Демонстрация использования аннотаций @TmsLink и @TmsLinks")
    public void demoAnnotationTmsLinksTest() {
        driver.get("http://www.sberbank.ru");
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Инвестиции')]"));
        Assert.assertTrue(element.isDisplayed());
    }

}
