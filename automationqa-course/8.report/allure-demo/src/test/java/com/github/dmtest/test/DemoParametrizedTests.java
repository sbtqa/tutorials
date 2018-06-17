package com.github.dmtest.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

/**
 * 5. В классе демонстрируется работа с параметризованными тестами.
 */
public class DemoParametrizedTests {
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

    @DataProvider(name = "Тестовые данные")
    private Object[][] getTestData() {
        return new Object[][] {
                {"Кредиты"}, {"Ипотека"}, {"Карты"}, {"Вклады"}, {"Платежи и переводы"}
        };
    }

    @Test(description = "Демонстрация работы с параметризованными тестами", dataProvider = "Тестовые данные")
    public void demoParametrizedTest(String data) {
        String xpath = String.format("//span[@class='lg-menu__text'][contains(text(),'%s')]", data);
        WebElement element = driver.findElement(By.xpath(xpath));
        Assert.assertTrue(element.isDisplayed());
    }
}
