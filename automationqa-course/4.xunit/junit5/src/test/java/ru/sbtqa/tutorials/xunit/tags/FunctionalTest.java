package ru.sbtqa.tutorials.xunit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Для проверки тестов с Tag("functional") и Tag("non-functional"), запустите тесты с профилем run-test-with-tags.
 * Будут запущены, только тесты functional, так как они включены в pom.xml использую groups
 */
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
