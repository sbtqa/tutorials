package com.github.dmtest.test;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.qameta.allure.Allure;
import io.qameta.allure.Attachment;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.ByteArrayInputStream;
import java.nio.charset.Charset;

/**
 * 3. В классе демонстрируется использование вложений.
 */
public class DemoAttachments {
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

    @Test(description = "Демонстрация использования вложений. Аннотация @Attachment")
    public void demoAttachmentsWithAnnotatedMethodTest() {
        attachScreenshot();
        attachTitle();
    }

    @Attachment
    private byte[] attachScreenshot() {
        return ((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES);
    }

    @Attachment(value = "Тайтл страницы", type = "text/plain", fileExtension = ".txt")
    private byte[] attachTitle() {
        return driver.getTitle().getBytes(Charset.forName("UTF-8"));
    }

    @Test(description = "Демонстрация использования вложений. Метод addAttachment")
    public void demoAttachmentsWithAddAttachmentMethodTest() {
        Allure.addAttachment("Скриншот", new ByteArrayInputStream(((TakesScreenshot) driver).getScreenshotAs(OutputType.BYTES)));
        Allure.addAttachment("Тайтл страницы", "text/plain", driver.getTitle(), ".txt");
    }
}
