package ru.sbtqa.tag.tutorials.allure;

import io.qameta.allure.Step;
import org.junit.jupiter.api.Assertions;

public class Steps {

    private Steps() {
        throw new IllegalAccessError("Utility class");
    }

    @Step
    public static void checkSumStep(int num1, int num2, int expectedSum) {
        Assertions.assertTrue(num1 + num2 == expectedSum, "Сумма слагаемых не соответствует ожидаемому значению");
    }

    @Step(value = "Проверка суммы числа {num1} и числа {num2}")
    public static void checkSummationStep(int num1, int num2, int expectedSum) {
        Assertions.assertTrue(num1 + num2 == expectedSum, "Сумма слагаемых не соответствует ожидаемому значению");
    }

    @Step(value = "Проверка разности числа {num1} и числа {num2}")
    public static void checkSubtractionStep(int num1, int num2, int expectedResult) {
        Assertions.assertTrue(num1 - num2 == expectedResult, "Результат вычитания не соответствует ожидаемому значению");
    }

    @Step(value = "Проверка синуса числа {num}")
    public static void checkSinStep(int num, double expectedResult) {
        Assertions.assertTrue(Math.sin(num) == expectedResult, "Результат выражения не соответствует ожидаемому значению");
    }
}
