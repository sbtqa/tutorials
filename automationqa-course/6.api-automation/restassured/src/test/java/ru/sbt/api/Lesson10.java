package ru.sbt.api;

import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import static io.restassured.RestAssured.when;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;

/**
 * валидация Schema
 */
public class Lesson10 extends RestAssuredConfig {

    @Test
    public void jsonValidation() {
        when().get(EndPoints.manufactures)
                .then()
                .body(matchesJsonSchemaInClasspath("schema/jsonschema.json"));
    }

}
