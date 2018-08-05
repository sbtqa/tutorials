package ru.sbt.api;

import io.restassured.filter.log.LogDetail;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.isOneOf;

/**
 * Простой запрос с логированием.
 * Если секция log прописана после вызова метода given() или when(), логироваться будет запрос
 * Если секция log прописана после вызова метода then(), логироваться будет ответ
 * После вызова метода log() должен быть вызван метод, определяющий уровень логирования.
 * Также после вызова метода log() можно вызвать метод ifValidationFails(),
 * в таком случае логирование будет произведено только в случае падения теста.
 * В метод ifValidationFails() можно передать объект перечисления, определяющий уровень логирования.
 * <p>
 * В секции then() доступны дополнительные условия для логирования:
 * ifError() - логировать ответ если код ответа >=400
 * ifStatusCodeIsEqualTo(int) - логировать ответ если статус код равен, переданному через аргумент коду
 * ifStatusCodeMatches(Matcher<Integer>) - логировать ответ, если статус код удовлетворяет переданному через аргумент матчеру
 *
 * Примечание: логирование осуществляется до вызова HTTP Client'а, который может добавить свои данные в запрос.
 * Для более детального анализа лога необходимо настроить логер для логированиея HTTP Client'a
 * (<a href="http://hc.apache.org/httpcomponents-client-ga/logging.html">HTTP Client logging docs</a>) или воспользоваться снифером HTTP-трафика
 */
public class Lesson2 {
    @Test
    public void test() {

//  глобальная настройка логирования:
//        RestAssured.config().logConfig(LogConfig.logConfig().enableLoggingOfRequestAndResponseIfValidationFails());

//        given()
//                .when().get("http://localhost:8080/car/manufacturers/1")
//                .then().statusCode(200).log().all();
//        given().log().all()
//                .when().get("http://localhost:8080/car/manufacturers/1")
//                .then().statusCode(200).log().all();
        given().log().ifValidationFails(LogDetail.ALL)
                .when().get("http://localhost:8080/car/manufacturers/1")
                .then().statusCode(200).log().ifStatusCodeMatches(isOneOf(200, 201));
    }
}
