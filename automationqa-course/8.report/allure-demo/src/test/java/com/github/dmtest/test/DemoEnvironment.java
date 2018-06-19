package com.github.dmtest.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 7. В классе демонстрируется работа с блоком ENVIRONMENT на главной странице отчета.
 */
public class DemoEnvironment {
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

    @Test(description = "Демонстрация работы с блоком ENVIRONMENT на главной странице отчета")
    public void demoEnvironmentBlockTest() {
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Поддержка')]"));
        Assert.assertTrue(element.isDisplayed());
    }
}
