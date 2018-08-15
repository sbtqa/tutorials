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
//        JsonSchemaFactory jsonSchemaFactory = JsonSchemaFactory.newBuilder()
//                .setValidationConfiguration(
//                        ValidationConfiguration.newBuilder().setDefaultVersion(SchemaVersion.DRAFTV4).freeze())
//                .freeze();

        when().get(EndPoints.manufactures)
                .then()
//                .body(matchesJsonSchemaInClasspath("schema/jsonschema.json").using(jsonSchemaFactory));
                .body(matchesJsonSchemaInClasspath("schema/jsonschema.json"));
    }

}
