package ru.sbtqa.patterns.creational;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.http.Cookie;
import io.restassured.specification.RequestSpecification;
import org.junit.Test;

/**
 * @author Alexey Rumyantsev
 */
public class BuilderTest {

    @Test
    public void test() {
        Cookie cookie = new Cookie.Builder("sessionId","123")
                .setComment("comment")
                .setHttpOnly(true)
                .setSecured(true)
                .build();

        RequestSpecification specs = new RequestSpecBuilder()
                .setContentType(ContentType.JSON)
                .addCookie(cookie)
                .addParam("param1", "value1")
                .log(LogDetail.ALL)
                .build();

        RestAssured.given()
                .spec(specs)
                .post("url1");
        RestAssured.given()
                .spec(specs)
                .post("url2");
    }
}
