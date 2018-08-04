package ru.sbt.api;

import io.restassured.path.json.config.JsonPathConfig;
import org.testng.annotations.Test;
import ru.sbt.api.endpoints.EndPoints;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.*;
import static io.restassured.config.JsonConfig.jsonConfig;

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
     * =~ - для применения регулярного выражения
     */
    @Test
    public void find() {
//        Map<String, ?> map = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}");
        Map<String, ?> map = get(EndPoints.manufactures).path("find { it.title =~ 'Toyota'}");
        System.out.println(map);
    }

    /**
     * Функция findAll позволяет собрать все данные удовлетворяющие условию
     */
    @Test
    public void findAll() {
        List list = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}.models.findAll { it.averagePrice > 2_000_000 }.title");
        System.out.println(list);
    }

    /**
     * Функция collect позволяет создать новую коллекцию из другой
     */
    @Test
    public void collect() {
        List toyotaCars = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}.models.collect { it.title }");
        List allCars = get(EndPoints.manufactures).path("collect { it.models }.collect { it.title }");
        System.out.println(toyotaCars);
        System.out.println(allCars);
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

    /**
     * Функция sum - возвращает сумму всех значений из коллекции
     */
    @Test
    public void sum() {
//        Double sum = get(EndPoints.manufactures).path("find { it.title == 'Toyota Motor Corporation'}.models.collect { it.averagePrice }.sum()");
        BigDecimal sum = given()
                .config(config()
                        .jsonConfig(jsonConfig().numberReturnType(JsonPathConfig.NumberReturnType.BIG_DECIMAL)))
                .get(EndPoints.manufactures)
                .path("find { it.title == 'Toyota Motor Corporation'}.models.collect { it.averagePrice }.sum()");
        System.out.println(sum);
    }
}
