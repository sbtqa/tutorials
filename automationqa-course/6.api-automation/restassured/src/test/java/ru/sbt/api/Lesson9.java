package ru.sbt.api;

import io.restassured.RestAssured;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;
import ru.sbt.api.pojo.Manufacturer;
import ru.sbt.api.pojo.Model;

import java.math.BigDecimal;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Сериализация Pojo класса в тело запроса
 */
public class Lesson9 extends RestAssuredConfig {

    @Test
    public void jsonPojoToBody() {
        RestAssured.responseSpecification = null;

        Model[] model = new Model[3];
        model[0] = new Model(null, "Octavia", new BigDecimal(1_300_000));
        model[1] = new Model(null, "Fabia", new BigDecimal(800_000));
        model[2] = new Model(null, "Rapid", new BigDecimal(750_000));
        Manufacturer manufacturer = new Manufacturer(null, model, "Škoda Auto", "Czech Republic");

        given().body(manufacturer)
                .when().post(EndPoints.manufactures)
                .then()
                .statusCode(201)
                .and()
                .body("id", not(isEmptyOrNullString()))
                .and()
                .extract().path("id");
    }

}
