package ru.sbt.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

/**
 * GPath
 */

public class Lesson12 extends RestAssuredConfig {
    private static Integer id;

    @Test
    public void getByPathParam() {
        given()
//                .pathParam("id", 1)
                .when().get(EndPoints.manufacture, 1)
                .then().log().all()
                .and()
                .body("title", is("Mazda")); //проверка тела ответа
    }

    @Test
    public void getByRequestParam() {
        given()
                .queryParam("id", 1)
                .when().get(EndPoints.manufactures)
                .then().log().all().and().body("title", is("Mazda"));
    }

    @Test(priority = 1)
    public void getAll() {
        given()
                .when().get(EndPoints.manufactures)
                .then().body("size()", is(3)); // проверяем количество элементов от корневого элемента
    }

    @Test(priority = 2)
    public void post() {
        String newElement = "{" +
                "  \"country\": \"Russia\"," +
                "  \"models\": [" +
                "    {" +
                "      \"title\": \"Vesta Cross\"" +
                "    }" +
                "  ]," +
                "  \"title\": \"Lada\"" +
                "}";

        RestAssured.responseSpecification = null;

        id = given().body(newElement)
                .when().post(EndPoints.manufactures)
                .then()
                .statusCode(201).log().body()
                .and()
                .body("id", not(isEmptyOrNullString()))
                .and()
                .extract().path("id");
    }

    @Test(priority = 3)
    public void update() {
        String updateElement = "{" +
                "  \"country\": \"Russia\"," +
                "  \"id\": " + id + "," +
                "  \"models\": [" +
                "    {" +
                "      \"title\": \"Vesta Cross\"," +
                "      \"title\": \"Kalina\"," +
                "      \"title\": \"Niva 4x4\"" +
                "    }" +
                "  ]," +
                "  \"title\": \"Lada\"" +
                "}";

        RestAssured.responseSpecification = null; // сбросить проверку expected status

        given().body(updateElement)
                .when().put(EndPoints.manufactures)
                .then()
                .statusCode(200).log().body()
                .and()
                .body(equalTo("{success}"));
    }

    @Test(priority = 4)
    public void delete() {
        given()
                .when().delete(EndPoints.manufacture, 1)
                .then().body(is("{success}"));
    }
}
