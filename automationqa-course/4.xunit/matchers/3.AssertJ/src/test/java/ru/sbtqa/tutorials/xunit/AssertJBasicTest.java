package ru.sbtqa.tutorials.xunit;


import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Примеры использования стандартных утверждений в fluent-стиле библиотеки AssertJ
 */
class AssertJBasicTest {

    class QA {
        private String name;
        private List<String> experience;

        QA(String name, List<String> experience) {
            this.name = name;
            this.experience = experience;
        }
    }

    /*
     * ============================
     * OBJECT ASSERTIONS
     * ============================
     */

    /**
     * Сравнение двух объектов. Сравнение происходит по свойству/полю "experience" класса QA
     */
    @Test
    void shouldWorkIsEqualToComparingOnlyGivenFields() {
        QA autoQA1 = new QA("Artem", Collections.singletonList("SberTech"));
        QA autoQA2 = new QA("Olga", Collections.singletonList("SberTech"));
        assertThat(autoQA1).isEqualToComparingOnlyGivenFields(autoQA2, "experience");
    }
}