package ru.sbt.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * Для уменьшения дублирования проверок, определяемых в секции ответа необходимо использовать спецификацию.
 * Объект класса ResponseSpecification собирается классом билдером ResponseSpecBuilder().
 * Передать спецификацию ответа в конкретный ответ можно используя метод spec() из секции then.
 * Также можно определить спецификацию для всех ответов, для этого необходимо присвоить созданную спецификацию
 * статическому полю responseSpecification класса RestAssured.
 */
public class Lesson5 {
    @Test
    public void test() {
        RequestSpecification requestSpecification = new RequestSpecBuilder()
                .setBaseUri("http://localhost:8080")
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.ANY)
                .log(LogDetail.ALL)
                .build();

        ResponseSpecification responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();

//        given().spec(requestSpecification)
//                .when().get("http://localhost:8080/car/manufacturers/1")
//                .then().spec(responseSpecification).log().all();

        RestAssured.requestSpecification = requestSpecification;
        RestAssured.responseSpecification = responseSpecification;

        given()
                .when().get("car/manufacturers/1")
                .then().log().all();
    }
}
