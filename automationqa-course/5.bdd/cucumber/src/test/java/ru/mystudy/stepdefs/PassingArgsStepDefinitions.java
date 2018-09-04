package ru.mystudy.stepdefs;

import cucumber.api.java.ru.ƒопустим;

/**
 * ¬ первой и второй версии Cucumber дл€ определени€ типа передаваемых данных использовались –егул€рные выражени€
 * и библиотека XStreamsConverters.
 * ¬ третьей версии Cucumber разработчики отказались от использовани€ библиотека XStreamsConverters
 * и добавили поддержку Cucumber Expressions.

 * ќбоснованием дл€ отказа от XStreamConverters была плоха€ документаци€, а так же отсутствие возможности
 * использовани€ сторонних мапперов объектов и поддержки Java 9.

 * Cucumber Expressions - это простой €зык выражений дл€ поиска подстрок в тексте. ¬ отличие от регул€рных выражений
 * он оптимизирован дл€ удобочитаемости, а не дл€ гибкости и контрол€, что в контексте Cucumber'а имеет больший смысл.
 */
public class PassingArgsStepDefinitions {
    @ƒопустим("передадим в метод шага целое число {int}")
    public void передадим_в_метод_шага_целое_число(Integer int1) {
        System.out.println(int1);
    }

    @ƒопустим("передадим в метод шага {string}")
    public void передадим_в_метод_шага(String string) {
        System.out.println(string);
    }
}
