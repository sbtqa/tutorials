package ru.sbt.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import static io.restassured.RestAssured.given;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * валидация Schema
 */
public class Lesson10 {

    @Test
    public void jsonValidation() {
        given().basePath("car").contentType(ContentType.JSON)
                .when().get(EndPoints.manufactures)
                .then()
                .statusCode(200).and().body(matchesJsonSchemaInClasspath("schema/jsonschema.json"));
    }

}
