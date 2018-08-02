package ru.sbt.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

/**
 * Получение и проверка времени получения ответа
 */
public class Lesson11 {

    @BeforeClass
    public void prepare() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "car";

        RestAssured.responseSpecification = RestAssured.expect().time(lessThan(3000L));

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .log(LogDetail.ALL)
                .setContentType(ContentType.JSON)
                .build();
    }


    @Test
    public void lessTime() {
//        long responseTime = given()
//                .when().get(EndPoints.manufacture, 1)
//                .getTime() // получить время в миллисекундах
//                .getTimeIn(TimeUnit.SECONDS);
//        System.out.println(responseTime);

        given()
                .when().get(EndPoints.manufacture, 1);
//                .then().time(lessThan(2000L));

    }

}
