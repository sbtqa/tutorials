package ru.sbtqa.tutorials.xunit;


import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Примеры использования стандартных утверждений в fluent-стиле библиотеки AssertJ
 */
class AssertJBasicTest {

    private QA autoQA1;
    private QA autoQA2;

    class QA {
        private String name;
        private List<String> experience;

        QA(String name) {
            this.name = name;
        }

        QA(String name, List<String> experience) {
            this.name = name;
            this.experience = experience;
        }
    }


    /*
     * =================================================
     * OBJECT ASSERTIONS. Класс AbstractObjectAssert API
     * =================================================
     */

    /**
     * Сравнение двух объектов. Сравнение происходит по свойству/полю "experience" класса QA.
     * Вызывается метод isEqualToComparingOnlyGivenFields у абстрактного класса AbstractObjectAssert
     */
    @Test
    void shouldWorkIsEqualToComparingOnlyGivenFields() {
        autoQA1 = new QA("Artem", Collections.singletonList("SberTech"));
        autoQA2 = new QA("Olga", Collections.singletonList("SberTech"));
        assertThat(autoQA1).isEqualToComparingOnlyGivenFields(autoQA2, "experience");
    }

    /**
     * Сравнение двух объектов, игнорирую не проиницилизированные свойства полей двух объектов
     */
    @Test
    void shouldWorkIsEqualToIgnoringNullFields() {
        autoQA1 = new QA("Dmitriy", null);
        autoQA2 = new QA("Dmitriy", null);
        assertThat(autoQA1).as("Сообщение при ошибке").isEqualToIgnoringNullFields(autoQA2);
    }

    /**
     * Сравниваем два объекта посредством сравнения полей объектов
     */
    @Test
    void shouldWorkIsEqualToComparingFieldByField() {
        autoQA1 = new QA("Dmitriy",  Collections.singletonList("Sber"));
        autoQA2 = new QA("Dmitriy",  Collections.singletonList("Sber"));
        assertThat(autoQA1).isEqualToComparingFieldByField(autoQA2);
    }

    /**
     * Проверяем, что у объекта Under Test есть свойство/поле с нужным именем
     */
    @Test
    void shouldWorkHasFieldOrProperty() {
        autoQA1 = new QA("Artem Sokovets",  Collections.singletonList("Google"));
        assertThat(autoQA1).hasFieldOrProperty("name").hasFieldOrProperty("experience");
    }

}