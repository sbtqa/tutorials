package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

@Disabled("Все тесты, которые есть в данном классе будут пропущены")
class AllDisabledTest {

    @Test
    void skippedFirstTest() {
        System.out.println("Тест не запускается");
    }

    @Test
    void skippedSecondTest() {
        System.out.println("Тест не запускается");
    }
}
