package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.api.Test;

/**
 * Фильтрацию по тегами можно делать на уровне IDEA, maven-surefire-plugin, Gradle, Command-line
 */
@Tag("regress")
class SimpleTaggingTest {

    @Test
    @Tag("WEB")
    void testingWEB() {
        Assertions.assertEquals(1 + 1, 2);
    }

    @Test
    @Tag("IOS")
    void testingIOS() {
        Assertions.assertEquals(1 + 2, 3);
    }

    @Test
    @Tag("ANDROID")
    void testingANDROID() {
        Assertions.assertEquals(1 + 3, 4);
    }
}