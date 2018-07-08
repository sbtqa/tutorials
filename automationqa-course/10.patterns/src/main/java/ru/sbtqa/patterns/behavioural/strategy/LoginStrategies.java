package ru.sbtqa.patterns.behavioural.strategy;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.response.Response;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import ru.sbtqa.patterns.creational.factory.java8.PrivilegedUser;
import ru.sbtqa.patterns.structural.adapter.SmsLoginUtils;

/**
 * @author Alexey Rumyantsev
 */
public final class LoginStrategies {
    public static void apiLogin(WebDriver webDriver) {
        Response response = RestAssured.given()
                .contentType(ContentType.JSON)
                .param("user", "admin")
                /*otherParams*/
                .post(webDriver.getCurrentUrl())
                .then()
                .assertThat().statusCode(200)
                .extract().response();


        //region convert cookies to selenium
        for (Cookie cookie : response.detailedCookies())
            webDriver.manage().addCookie(new org.openqa.selenium.Cookie.Builder(
                    cookie.getName(), cookie.getValue())
                    .path(cookie.getPath())
                    .domain(cookie.getDomain())
                    .isHttpOnly(cookie.isHttpOnly())
                    .isSecure(cookie.isSecured())
                    .expiresOn(cookie.getExpiryDate())
                    .build());
        //endregion

        webDriver.navigate().to("Main Page");

    }

    public static void formLogin(WebDriver webDriver) {
        webDriver.findElement(By.id("username")).sendKeys("admin");
        webDriver.findElement(By.id("password")).sendKeys("admin");
        webDriver.findElement(By.id("submit")).click();
        /**/
    }

    public static void smsCodeLogin(WebDriver webDriver) {
        SmsLoginUtils.loginBySms(new PrivilegedUser());
    }
}
