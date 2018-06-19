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
 * 6. В классе демонстрируется работа с категориями дефектов.
 */
public class DemoCategories {
    private WebDriver driver;

    @BeforeMethod(description = "Старт драйвера")
    private void startDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
    }

    @AfterMethod(description = "Стоп драйвера")
    private void quitDriver() {
        driver.quit();
    }

    @Test(description = "Демонстрация работы с категориями дефектов. Некорректный URL")
    public void demoCategoriesTest1() {
        driver.get("www.sberbank.ru");
    }

    @Test(description = "Демонстрация работы с категориями дефектов. Устаревший XPATH")
    public void demoCategoriesTest2() {
        driver.get("http://www.sberbank.ru");
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Нет такого текста')]"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Test(description = "Демонстрация работы с категориями дефектов. Дефект продукта")
    public void demoCategoriesTest3() {
        driver.get("http://www.sberbank.ru");
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Инвестиции')]"));
        Assert.assertTrue(!element.isDisplayed());
    }

}
