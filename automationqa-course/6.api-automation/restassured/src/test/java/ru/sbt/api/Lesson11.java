package ru.sbt.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

/**
 * Получение и проверка времени получения ответа
 */
public class Lesson11 extends RestAssuredConfig{

    @Test
    public void lessTime() {
        RestAssured.responseSpecification = RestAssured.expect().time(lessThan(3000L));


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
