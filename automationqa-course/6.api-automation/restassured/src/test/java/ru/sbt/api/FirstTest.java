package ru.sbt.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

public class FirstTest {
    @Test
    public void firstTest() {
        given().when().get("http://localhost:8080/car/manufacturers/1").then().statusCode(200).log().all();
//        given().accept(ContentType.XML).when().get("http://localhost:8080/car/manufacturers/1").then().statusCode(200).log().all();
    }
}
