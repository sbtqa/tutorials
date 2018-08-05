package ru.sbt.api;

import org.testng.annotations.Test;

import java.util.Map;

import static io.restassured.RestAssured.given;

/**
 * В данном уроке показано как можно получить доступ к сервису, защищенному при помощи HTTP Basic и Digest Authentication
 * Продеманстрировано как можно можно получить cookies из ответа, а также сокращенная запись получения идентификатора сессии
 *
 */
public class Lesson8 {

    /**
     * При HTTP Basic Authentication логин и пароль передаются в кодированном Base64 виде
     */
    @Test
    public void extractCookiesAsMap() {
        Map<String, String> cookies = given().auth().basic("user", "password")
                .when().get("secured/app/").cookies();
        System.out.println(cookies);
    }

    /**
     * При HTTP Digest Authentication логин и пароль в виде хеш-суммы функции MD5
     */
    @Test
    public void extractSessionIdAsString() {
        String sessionId = given().auth().digest("user", "password")
                .when().get("secured/app/").sessionId();
        System.out.println(sessionId);
    }
}
