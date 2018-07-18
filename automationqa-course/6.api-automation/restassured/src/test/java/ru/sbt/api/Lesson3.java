package ru.sbt.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;

/**
 * В HTTP для запроса данных в определенном формате используется заголовок - Accept.
 * Для передачи информации о формате передаваемой информации используется заголовок - Content-Type.
 * Данные заголовки можно передать в запрос через метод header(),
 * но REST-assured предоставляет удобные методы, которые делают тоже самое в более читабельном виде - accept() и contentType().
 * Вызов данных методов должен быть осуществлен в секции given или when.
 */
public class Lesson3 {
    @Test
    public void test() {
//        given().log().all().header("accept", "application/xml")
//                .when().get("http://localhost:8080/car/manufacturers/1")
//                .then().statusCode(200).log().all();

        given().log().all().accept(ContentType.XML)
                .contentType(ContentType.ANY)
                .when().get("http://localhost:8080/car/manufacturers/1")
                .then().statusCode(200).log().all();
    }
}
