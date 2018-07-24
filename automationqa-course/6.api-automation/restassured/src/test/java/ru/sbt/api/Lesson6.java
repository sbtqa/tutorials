package ru.sbt.api;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.LogDetail;
import io.restassured.http.ContentType;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.is;

public class Lesson6 {
    @BeforeClass
    public void prepare() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 8080;
        RestAssured.basePath = "car";

        RestAssured.requestSpecification = new RequestSpecBuilder()
                .setAccept(ContentType.JSON)
                .setContentType(ContentType.JSON)
                .log(LogDetail.ALL)
                .build();

        RestAssured.responseSpecification = new ResponseSpecBuilder()
                .expectStatusCode(200)
                .build();
    }

    @Test(priority = 1)
    public void get() {
        given()
                .when().get(EndPoints.manufacturs)
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

        Integer newElementID = given().body(newElement)
                .when().post(EndPoints.manufacturs)
                .then().statusCode(201)
                .log().body()
                .and()
                .extract().path("id");

        System.out.println(Integer.toString(newElementID));
    }

    @Test
    public void update() {

    }

    @Test
    public void delete() {

    }
}
