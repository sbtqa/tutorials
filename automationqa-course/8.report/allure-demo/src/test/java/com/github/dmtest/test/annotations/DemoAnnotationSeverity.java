package com.github.dmtest.test.annotations;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Severity;
import io.qameta.allure.SeverityLevel;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 4.6 В классе демонстрируется использование аннотации {@link Severity}.
 */
public class DemoAnnotationSeverity {
    private WebDriver driver;

    @BeforeMethod(description = "Старт драйвера. Переход на главную страницу Сбербанка")
    private void openPage() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru");
    }

    @AfterMethod(description = "Стоп драйвера")
    private void quitDriver() {
        driver.quit();
    }

    @Severity(SeverityLevel.CRITICAL)
    @Test(description = "Демонстрация использования аннотации @Severity")
    public void demoAnnotationFlakyTest() {
        WebElement element = driver.findElement(By.xpath("//a[@class='segments__link'][contains(text(),'О банке')]"));
        Assert.assertTrue(element.isDisplayed());
    }


}
