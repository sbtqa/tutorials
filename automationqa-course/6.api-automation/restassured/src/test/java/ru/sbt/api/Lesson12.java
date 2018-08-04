package ru.sbt.api;

import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.get;

/**
 * Для доступа к данным тела ответа в REST-assured применяется встроенный языка запросов JsonPath.
 * JsonPath является альтернативой XPath.
 * <p>
 * JsonPath для навигации и доступа к данным использует Groovy GPath синаксис.
 * Данный синаксис содержит функции find, findAll, max, min collect, и sum, которые принимают в качестве аргумента замыкание.
 * В функции замыкании (closure) для доступа к текущему значению документа используется специальная переменная - it
 */

public class Lesson12 extends RestAssuredConfig {


    /**
     * Функция find применяется для поиска первого вхождения
     */
    @Test
    public void find () {
        Map<String, ?> map = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}");
        System.out.println(map);
    }

    /**
     * Функция findAll позволяет собрать все данные удовлетворяющие условию
     */
    @Test
    public void findAll () {
        List list = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}.models.findAll { it.averagePrice > 2_000_000 }.title");
        System.out.println(list);
    }

    /**
     * Функция max находит максимальное значение из коллекции, min - минимальное
     */
    @Test
    public void minAndMax() {
        String cheepCar = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}.models.min { it.averagePrice }.title");
        String expensiveCar = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}.models.max { it.averagePrice }.title");
        System.out.println(cheepCar);
        System.out.println(expensiveCar);
    }
}
