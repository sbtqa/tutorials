package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("Арифметические операции")
class DisplayNameTest {

    @DisplayName("Операция сложения")
    @Test
    void simpleTestOne() {
        Assertions.assertEquals(5 + 5, 10,
                "Ошибка при вычислении операции сложения");
    }

    @DisplayName("Операция вычитания")
    @Test
    void simpleTestTwo() {
        Assertions.assertEquals(15 - 5, 10,
                "Ошибка при вычислении операции вычитания");
    }
}
