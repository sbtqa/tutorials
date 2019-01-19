package ru.sbtqa.tutorials.xunit.annotations;

import org.junit.jupiter.api.Test;

class NonFunctionalWithMetaAnnotationsTest {

    @Test
    @Load
    void testOne() {
        System.out.println("Не функциональный тест 1 (Performance/Load)");
    }

    @Test
    @Stress
    void testTwo() {
        System.out.println("Не функциональный тест 2 (Performance/Stress)");
    }

    @Test
    @Security
    void testThree() {
        System.out.println("Не функциональный тест 3 (Security)");
    }

    @Test
    @Usability
    void testFour() {
        System.out.println("Не функциональный тест 4 (Usability)");
    }
}
