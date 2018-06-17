package com.github.dmtest.test.annotations;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

/**
 * 4.2 В классе демонстрируется использование функциональных аннотаций: {@link Epic}, {@link Epics}, {@link Feature},
 * {@link Features}, {@link Story}, {@link Stories}.
 */
public class DemoAnnotationsFunctionality {
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

    @Epic("Отображение главного меню")
    @Feature("Отображение кнопки 'Карты' в главном меню")
    @Story("Как пользователь, я должен видеть кнопку 'Карты' в главном меню")
    @Test(description = "Демонстрация использования функциональных аннотаций. Тест 1")
    public void demoAnnotationsFunctionalityTest1() {
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Карты')]"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Epic("Отображение главного меню")
    @Feature("Отображение кнопки 'Вклады' в главном меню")
    @Story("Как пользователь, я должен видеть кнопку 'Вклады' в главном меню")
    @Test(description = "Демонстрация использования функциональных аннотаций. Тест 2")
    public void demoAnnotationsFunctionalityTest2() {
        WebElement element = driver.findElement(By.xpath("//span[@class='lg-menu__text'][contains(text(),'Вклады')]"));
        Assert.assertTrue(element.isDisplayed());
    }

    @Epics({@Epic("Навигация по страницам"), @Epic("Отображение главного меню")})
    @Features({@Feature("Навигация 'Частным клиентам' -> 'Малому бизнесу и ИП'"), @Feature("Отображение кнопки 'Кредиты и гарантии' в главном меню")})
    @Stories({@Story("Как пользователь, я должен видеть кнопку 'Частным клиентам' в главном меню"),
            @Story("Как пользователь, я должен видеть кнопку 'Кредиты и гарантии' в главном меню")})
    @Test(description = "Демонстрация использования функциональных аннотаций. Тест3")
    public void demoAnnotationsFunctionalityTest3() {
        String smallBusinessXpath = "//a[@class='segments__link'][contains(text(),'Малому бизнесу и ИП')]";
        WebElement smallBusinessElement = driver.findElement(By.xpath(smallBusinessXpath));
        smallBusinessElement.click();
        String creditsAndWarrantyXpath = "//span[@class='multiline'][contains(text(),'Кредиты и гарантии')]";
        WebElement creditsAndWarrantyElement = new WebDriverWait(driver, 10).until((WebDriver d) -> d.findElement(By.xpath(creditsAndWarrantyXpath)));
        Assert.assertTrue(creditsAndWarrantyElement.isDisplayed());
    }
}
