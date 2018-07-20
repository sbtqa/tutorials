package ru.sbt.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Для уменьшения дублирования параметров, определяемых в разных запросах необходимо использовать спецификацию.
 * Объект класса RequestSpecification собирается классом билдером RequestSpecBuilder().
 * Передать спецификацию запроса в конкретный запрос можно используя метод spec() из секции given или when.
 * Также можно определить спецификацию для всех запросов, для этого необходимо присвоить созданную спецификацию
 * статическому полю requestSpecification класса RestAssured
 */
public class Lesson4 {
    @Test
    public void test() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAccept(ContentType.XML)
                .setContentType(ContentType.ANY)
                .log(LogDetail.ALL)
                .build();

//        given().spec(requestSpecification)
//                .when().get("http://localhost:8080/car/manufacturers/1")
//                .then().statusCode(200).log().all();

        RestAssured.requestSpecification = requestSpecification;

        given()
                .when().get("car/manufacturers/1")
                .then().statusCode(200).log().all();
    }
}
