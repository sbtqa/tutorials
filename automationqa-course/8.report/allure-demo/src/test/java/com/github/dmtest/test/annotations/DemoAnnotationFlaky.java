package com.github.dmtest.test.annotations;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Flaky;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.concurrent.ThreadLocalRandom;

/**
 * 4.3 В классе демонстрируется использование аннотации {@link Flaky}.
 */
public class DemoAnnotationFlaky {
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

    @Flaky
    @Test(description = "Демонстрация использования аннотации @Flaky", invocationCount = 5)
    public void demoAnnotationFlakyTest() {
        WebElement element = driver.findElement(By.xpath(getXpath()));
        Assert.assertTrue(element.isDisplayed());
    }

    private String getXpath() {
        String xpathTmp = "//span[@class='lg-menu__text'][contains(text(),'%s')]";
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        return randomNum == 0 ? String.format(xpathTmp, "Страхование") : String.format(xpathTmp, "Нет такого текста");
    }
}
