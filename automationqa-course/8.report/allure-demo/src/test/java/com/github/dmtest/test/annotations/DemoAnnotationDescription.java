package com.github.dmtest.test.annotations;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Description;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 4.1 В классе демонстрируется использование аннотации {@link Description}.
 */
public class DemoAnnotationDescription {
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

    @Test(description = "Демонстрация использования аннотации @Description")
    @Description("В данном тесте демонстрируется применение аннотации @Description. Тест проверяет видимость элемента 'Ипотека'")
    public void demoAnnotationDescriptionTest() {
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Ипотека')]"));
        Assert.assertTrue(element.isDisplayed());
    }

    /**
     * This test demonstrates the use of the @Description annotation with the {@link Description#useJavaDoc()} parameter.
     * Test checks the visibility of element 'Card'
     */
    @Test(description = "Демонстрация использования аннотации @Description с параметром useJavaDoc")
    @Description(useJavaDoc = true)
    public void demoAnnotationDescriptionUseJavaDocTest() {
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Вклады')]"));
        Assert.assertTrue(element.isDisplayed());
    }

}

