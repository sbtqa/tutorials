package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

class DisabledTest {

    @Disabled
    @Test
    void skippedTest() {
        System.out.println("Данный тест не запускается");
    }

}
