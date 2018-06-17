package com.github.dmtest.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

/**
 * 1. В классе демонстрируется построение простого Allure-отчета.
 */
public class DemoSuccessConnection {

    @Test(description = "Демонстрация успешности подключения Allure к проекту")
    public void demoSuccessConnectionTest() {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("http://www.sberbank.ru");
        driver.quit();
    }
}
