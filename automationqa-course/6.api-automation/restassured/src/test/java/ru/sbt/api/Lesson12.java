package ru.sbt.api;

import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import java.util.Map;

import static io.restassured.RestAssured.get;

/**
 * Для доступа к данным тела ответа в REST-assured применяется языка запросов JsonPath.
 * JsonPath является альтернативой XPath.
 * <p>
 * JsonPath для навигации и доступа к данным использует Groovy GPath синаксис
 * Данный синаксис позволяет применять фукнции-замыкания к содержимому тела ответа. Для доступа к текущему значению
 * функции используется специальная переменная - it
 */

public class Lesson12 extends RestAssuredConfig {

    @Test
    public void find() {
        Map<String, ?> map = get(EndPoints.manufactures).path("find { it.id == 7}");
        System.out.println(map);
    }
}
