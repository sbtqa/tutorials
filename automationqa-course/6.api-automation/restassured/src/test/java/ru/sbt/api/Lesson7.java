package ru.sbt.api;

import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsNot.not;
import static ru.sbt.api.endpoints.EndPoints.manufactures;


/**
 * В данномм уроке показаны различные способы получения данных из ответа
 */
public class Lesson7 extends RestAssuredConfig {

    @Test
    public void extractBodyAsString() {
        String body = given().basePath("car").get(manufactures).asString();
        System.out.println(body);
    }

    @Test
    public void assertAndExtractBodyAsString() {
        String body = given().basePath("car").get(manufactures).then().body(not(empty())).extract().asString();
        System.out.println(body);
    }
}
