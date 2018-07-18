package ru.sbt.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Простой запрос с проверкой возвращаемого статуса - 200 OK
 */
public class Lesson1 {
    @Test
    public void test() {
        given()
                .when().get("http://localhost:8080/car/manufacturers/1")
                .then().statusCode(200);
    }
}
