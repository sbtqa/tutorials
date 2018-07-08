package ru.sbtqa.patterns.structural.proxy;

import io.restassured.http.ContentType;

import static io.restassured.RestAssured.given;

public class RealServer implements Server {

    private String url;

    public RealServer(String url) {
        this.url = url;
    }

    @Override
    public String sendMessage(String message) {
        return given()
                .contentType(ContentType.JSON)
                .param("message", message)
                .post(url)
                .then()
                .extract().jsonPath().getString("body");
    }
}
