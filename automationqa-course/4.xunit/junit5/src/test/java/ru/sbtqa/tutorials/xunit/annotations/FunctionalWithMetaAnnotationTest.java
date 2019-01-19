package ru.sbtqa.tutorials.xunit.annotations;

import org.junit.jupiter.api.Test;

@Functional
class FunctionalWithMetaAnnotationTest {

    @Test
    void testOne() {
        System.out.println("Функциональный тест 1");
    }

    @Test
    void testTwo() {
        System.out.println("Функциональный тест 2");
    }
}
