package ru.sbtqa.tutorials.xunit.tags;

import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Tags;
import org.junit.jupiter.api.Test;

/**
 * Для проверки тестов с Tag("functional") и Tag("non-functional"), запустите тесты с профилем run-test-with-tags.
 * Будут запущены, только тесты, так как non-functional отключены в pom.xml использую excludedGroups
 */
@Tag("non-functional")
class NonFunctionalTest {

    /**
     * Демонстрация использования @Tags
     */
    @Test
    @Tags({@Tag("performance"), @Tag("load")})
    void testOne() {
        System.out.println("Не функциональный тест 1 (Performance/Load)");
    }

    @Test
    @Tags({@Tag("performance"), @Tag("stress")})
    void testTwo() {
        System.out.println("Не функциональный тест 2 (Performance/Stress)");
    }

    @Test
    @Tag("security")
    void testThree() {
        System.out.println("Не функциональный тест 3 (Security)");
    }

    @Test
    @Tag("usability")
    void testFour() {
        System.out.println("Не функциональный тест 4 (Usability)");
    }
}
