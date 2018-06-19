package com.github.dmtest.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * 2. В классе демонстрируется использование тестовых шагов.
 */
public class DemoSteps {
    private WebDriver driver;

    @Test(description = "Демонстрация использования тестовых шагов. Аннотация @Step")
    public void demoStepsTest() {
        openPage("http://www.sberbank.ru");
        checkElementIsDisplayed("//div[@class='lg-menu']//span[text()='Кредиты']");
        quitDriver();
    }

    @Step("Открывает страницу Сбербанка")
    private void openPage(String url) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Step("Проверяет отображение элемента с xpath {xpath}")
    private void checkElementIsDisplayed(String xpath) {
        WebElement creditsSpan = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(creditsSpan.isDisplayed());
    }

    @Step
    private void quitDriver() {
        driver.quit();
    }

}
