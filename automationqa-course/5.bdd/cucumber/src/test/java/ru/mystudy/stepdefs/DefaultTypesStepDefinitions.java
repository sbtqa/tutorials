package ru.mystudy.stepdefs;

import cucumber.api.java.AfterStep;
import cucumber.api.java.BeforeStep;
import cucumber.api.java.ru.ƒопустим;

import java.util.List;
import java.util.Map;

/**
 * ¬ первой и второй версии Cucumber дл€ определени€ типа передаваемых данных использовались –егул€рные выражени€
 * и библиотека XStreamsConverters.
 * ¬ третьей версии Cucumber разработчики отказались от использовани€ библиотека XStreamsConverters
 * и добавили поддержку Cucumber Expressions.
 * <p>
 * ќбоснованием дл€ отказа от XStreamConverters была плоха€ документаци€, отсутствие возможности
 * использовани€ сторонних мапперов объектов, а так же отсутствие поддержки Java 9.
 * <p>
 * Cucumber Expressions - это простой €зык выражений дл€ поиска подстрок в тексте. ¬ отличие от регул€рных выражений
 * он оптимизирован дл€ удобочитаемости, а не дл€ гибкости и контрол€, что в контексте Cucumber'а имеет больший смысл.
 */
public class DefaultTypesStepDefinitions {
    @ƒопустим("передадим в метод шага целое число {int}")
    public void передадим_в_метод_шага_целое_число(Integer int1) {
        System.out.println(int1);
    }

    @ƒопустим("передадим в метод шага {string}")
    public void передадим_в_метод_шага(String string) {
        System.out.println(string);
    }

    @ƒопустим("передадим в метод шага {word}")
    public void передадим_в_метод_шага_слово(String string) {
        System.out.println(string);
    }

    @ƒопустим("передадим в метод коллекцию строк")
    public void передадим_в_метод_коллекцию_строк(List<String> dataTable) {
        System.out.println(dataTable);
    }

    @ƒопустим("передадим в метод ассоциативный массив")
    public void передадим_в_метод_ассоциативный_массив(Map<String, String> dataTable) {
        System.out.println(dataTable);
    }
}
