package ru.sbtqa.tag.tutorials.allure;

import io.qameta.allure.Allure;
import io.qameta.allure.Step;
import org.testng.Assert;

import java.io.IOException;

public class Steps {

    @Step
    public static void checkSumStep(int num1, int num2, int expectedSum) {
        Assert.assertTrue(num1 + num2 == expectedSum, "Сумма слагаемых не соответствует ожидаемому значению");
    }

    @Step(value = "Проверка суммы числа {num1} и числа {num2}")
    public static void checkSummationStep(int num1, int num2, int expectedSum) {
        Assert.assertTrue(num1 + num2 == expectedSum, "Сумма слагаемых не соответствует ожидаемому значению");
    }

    @Step(value = "Проверка разности числа {num1} и числа {num2}")
    public static void checkSubtractionStep(int num1, int num2, int expectedResult) {
        Assert.assertTrue(num1 - num2 == expectedResult, "Результат вычитания не соответствует ожидаемому значению");
    }

    @Step(value = "Проверка эквивалентности строки {str1} строке {str2}")
    public static void checkStringEqualsStep(String str1, String str2) throws IOException {
        Assert.assertTrue(str1.equals(str2), "Строки не эквивалентны");
        CommonFunctions.getBytes("attachments/picture.jpg");
        CommonFunctions.getBytes("attachments/text.txt");
    }

    @Step(value = "Зачитать джейсон")
    public static void readJsonStep() throws IOException {
        CommonFunctions.getBytesAnnotationWithArgs("attachments/json.json");
    }

    @Step(value = "Добавить ссылку на Сбербанк")
    public static void addLinkSber() {
        String link = "http://sberbank.ru";
        Allure.addAttachment("Результат", "text/plain", link);
    }

    @Step(value = "Проверка синуса числа {num}")
    public static void checkSinStep(int num, double expectedResult) {
        Assert.assertTrue(Math.sin(num) == expectedResult, "Результат выражения не соответствует ожидаемому значению");
    }
}
