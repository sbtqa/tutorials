package ru.sbtqa.tag.tutorials.allure;

import io.qameta.allure.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.concurrent.ThreadLocalRandom;

class TestClass {
    private static final Logger LOG = LoggerFactory.getLogger(TestClass.class);

    @BeforeEach
    void beforeTest() {
        LOG.info("Тест стартует!");
    }

    @AfterEach
    void afterTest() {
        LOG.info("Тест завершен!");
    }

    @Test
    void testDemoConnectionSuccess() {
        Assertions.assertTrue(true);
    }

    @Test
    void testDemoAttachments2() throws IOException {
        CommonFunctions.getBytesAnnotationWithArgs("json.json");
        Assertions.assertTrue(true);
    }

    @Test
    void testDemoAttachments3() {
        Allure.addAttachment("Ссылка", "text/plain", "http://sberbank.ru");
        Assertions.assertTrue(true);
    }

    /**
     * Simple test for @Description illustration
     */
    @Test
    @Description(useJavaDoc = true)
    void testDemoDescriptionAnnotation1() {
        Assertions.assertTrue(1 == 1);
    }

    @Test
    void testDemoSteps1() {
        Steps.checkSumStep(3, 2, 5);
        Steps.checkSumStep(5, 4, 9);
    }

    @Test
    void testDemoSteps2() {
        Steps.checkSubtractionStep(10, 8, 2);
    }

    @Test
    void testDemoAttachments1() throws IOException {
        CommonFunctions.getBytes("picture.jpg");
        CommonFunctions.getBytes("text.txt");
        Assertions.assertTrue(true);
    }

    @Test
    @Description(value = "Тест проверяет эквивалентность единицы единице")
    void testDemoDescriptionAnnotation2() {
        Assertions.assertTrue(1 == 1);
    }

    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Сложение")
    @Test
    void testDemoFunctionalAnnotations1() {
        Steps.checkSummationStep(5, 4, 9);
    }

    @Epic(value = "Математика")
    @Feature(value = "Простые математические операции")
    @Story(value = "Вычитание")
    @Test
    void testDemoFunctionalAnnotations2() {
        Steps.checkSubtractionStep(8, 2, 6);
    }

    @Epics(value = {@Epic(value = "Математика"), @Epic(value = "Геометрия")})
    @Features(value = {@Feature(value = "Тригонометрия"), @Feature(value = "Простые математические операции")})
    @Stories(value = {@Story(value = "Синус"), @Story(value = "Синусоида")})
    @Test
    void testDemoFunctionalAnnotations3() {
        Steps.checkSinStep(0, 0);
    }

    @Test
    @Flaky
    void testDemoFlakyAnnotation() {
        int randomNum = ThreadLocalRandom.current().nextInt(0, 2);
        if (randomNum == 0) {
            Assertions.assertTrue(true);
        } else {
            Assertions.assertTrue(false);
        }
    }

    @Test
    @Issue(value = "FGY-4627")
    void testDemoIssueAnnotation() {
        Assertions.assertTrue(1 + 2 == 4);
    }

    @Test
    @TmsLink(value = "TL-678")
    void testDemoTmsLinkAnnotation() {
        Assertions.assertTrue(1 == 1);
    }

    @Test
    @Link(name = "Ссылка", url = "http://yandex.ru")
    void testDemoLinkAnnotation() {
        Steps.checkSubtractionStep(15, 5, 10);
    }

    @Test
    @Links(value = {@Link(name = "Ссылка1", url = "http://sberbank.ru"),
            @Link(name = "Ссылка2", url = "http://yandex.ru")})
    void testDemoLinksAnnotation() {
        Steps.checkSubtractionStep(14, 5, 9);
    }

    //Демонстрация работы категорий. Product defects
    @Test
    void testDemoCategories1() {
        //какие-то проверки, в результате которых тест может упасть
        Steps.checkSumStep(5, 6, 4);
    }

    //Демонстрация работы категорий. Test defects. traceRegex
    @Test
    void testDemoCategories2() {
        //какие-то проверки, которые могут вызывать NullPointerException
        throw new NullPointerException();
    }

    //Демонстрация работы категорий. Test defects. messageRegex
    @Test
    void testDemoCategories3() throws Exception {
        //какие-то проверки, которые могут вызывать Exception
        throw new Exception("что-то пошло не так");
    }

    @Test
    @Owner(value = "Пупкин Валерий Иванович")
    void testDemoOwnerAnnotation() {
        Steps.checkSumStep(1, 2, 3);
    }

    @Test
    @Severity(value = SeverityLevel.BLOCKER)
    void testDemoSeverityAnnotation() {
        Steps.checkSubtractionStep(6, 1, 5);
    }
}
