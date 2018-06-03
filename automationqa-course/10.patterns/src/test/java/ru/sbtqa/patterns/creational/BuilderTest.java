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
        //static class
        Cookie cookie = new Cookie.Builder("sessionId", "some_id")
                .setComment("comment")
                .setHttpOnly(true)
                .setSecured(true)
                .build();


        RequestSpecBuilder builder = new RequestSpecBuilder();
        builder.setContentType(ContentType.JSON)
                .addCookie(cookie)
                .addParam("parameter1","value")
                .setBody("{\"username\": \"123\"}")
                .log(LogDetail.ALL);

        RestAssured.given()
                .spec(builder.build())
                .post("some_url");
    }
}
