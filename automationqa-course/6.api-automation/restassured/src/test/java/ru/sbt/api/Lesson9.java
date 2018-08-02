package ru.sbt.api;

import io.restassured.http.ContentType;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;
import ru.sbt.api.pojo.Manufacturer;
import ru.sbt.api.pojo.Model;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isEmptyOrNullString;
import static org.hamcrest.Matchers.not;

/**
 * Сериализация Pojo класса в тело запроса
 */
public class Lesson9 {

    @Test
    public void jsonPojoToBody() {
        Model[] model = new Model[3];
        model[0] = new Model(null, "Octavia");
        model[1] = new Model(null, "Fabia");
        model[2] = new Model(null, "Rapid");
        Manufacturer manufacturer = new Manufacturer(null, model, "Škoda Auto", "Czech Republic");

        given().basePath("car").contentType(ContentType.JSON).body(manufacturer)
                .when().post(EndPoints.manufactures)
                .then()
                .statusCode(201).log().body()
                .and()
                .body("id", not(isEmptyOrNullString()))
                .and()
                .extract().path("id");
    }

}
