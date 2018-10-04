package ru.mystudy.stepdefs;

import cucumber.api.java.ru.Допустим;
import io.cucumber.datatable.DataTable;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.List;
import java.util.Map;

/**
 * В первой и второй версии Cucumber для определения типа передаваемых данных использовались Регулярные выражения
 * и библиотека XStreamsConverters.
 * В третьей версии Cucumber разработчики отказались от использования библиотека XStreamsConverters
 * и добавили поддержку Cucumber Expressions.
 * <p>
 * Обоснованием для отказа от XStreamConverters была плохая документация, отсутствие возможности
 * использования сторонних мапперов объектов, а так же отсутствие поддержки Java 9.
 * <p>
 * Cucumber Expressions - это простой язык выражений для поиска подстрок в тексте. В отличие от регулярных выражений
 * он оптимизирован для удобочитаемости, а не для гибкости и контроля, что в контексте Cucumber'а имеет больший смысл.
 */
public class DefaultTypesStepDefinitions {
    @Допустим("передадим в метод шага целое число {int}")
    public void giveInt(Integer int1) {
        System.out.println(int1);
    }

    @Допустим("передадим в метод шага {string}")
    public void giveString(String string) {
        System.out.println(string);
    }

    @Допустим("передадим в метод шага {word}")
    public void giveWord(String string) {
        System.out.println(string);
    }

    @Допустим("передадим в метод шага BigInteger {biginteger}")
    public void giveWord(BigInteger integer) {
        System.out.println(integer);
    }

    @Допустим("Hello, world(s)!")
    public void getHello() {
        System.out.println("Hello world!");
    }

    @Допустим("основной/альтернативный текст")
    public void getAlternative() {
        System.out.println("Hello world!");
    }

    @Допустим("передадим в метод коллекцию строк")
    public void передадим_в_метод_коллекцию_строк(List<String> dataTable) {
        System.out.println(dataTable);
    }

    @Допустим("передадим в метод коллекцию bigdecimal")
    public void передадим_в_метод_коллекцию_bd(List<BigDecimal> dataTable) {
        System.out.println(dataTable);
    }

    @Допустим("передадим в метод ассоциативный массив")
    public void передадим_в_метод_ассоциативный_массив(Map<String, String> dataTable) {
        System.out.println(dataTable);
    }

    @Допустим("передадим в метод datatable")
    public void передадим_в_метод_datatable(DataTable dataTable) {
        for (int i = 0; i < dataTable.width(); i++) {
            System.out.println(dataTable.column(i));
        }
    }
}
