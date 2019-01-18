package ru.sbtqa.tutorials.xunit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

@Tag("functional")
class FunctionalTest {

    @Test
    void testOne() {
        System.out.println("Функциональный тест 1");
    }

    @Test
    void testTwo() {
        System.out.println("Функциональный тест 2");
    }
}
