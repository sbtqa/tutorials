package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.*;

/**
 * Основыне аннотации Junit 5 для управления жизненным циклом автоматизированного теста/-ов
 */
class LifecycleJUnit5Test {

    @BeforeAll
    static void setUpAll() {
        System.out.println("Подготовка тестового окружения ПЕРЕД ВСЕМИ тестами");
    }

    @BeforeEach
    void setUp() {
        System.out.println("Подготовка тестового окружения ПЕРЕД КАЖДЫМ тестом");
    }

    @Test
    void simpleTestOne() {
        Assertions.assertEquals(5 + 5, 10,
                "Ошибка при вычислении операции сложения");
    }

    @Test
    void simpleTestTwo() {
        Assertions.assertEquals(15 - 5, 10,
                "Ошибка при вычислении операции вычитания");
    }

    @AfterEach
    void tearDown() {
        System.out.println("Освобождение ресурсов ПОСЛЕ КАЖДОГО теста");
    }

    @AfterAll
    static void tearDownAll() {
        System.out.println("Освобождение ресурсов ПОСЛЕ ВСЕХ тестов");
    }
}
