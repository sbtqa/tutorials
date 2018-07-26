package ru.sbt.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.core.IsNot.not;
import static ru.sbt.api.EndPoints.manufactures;


/**
 * В данномм уроке показаны различные способы получения данных из ответа
 */
public class Lesson7 {
    @BeforeClass
    public void prepare() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .build();
    }

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
