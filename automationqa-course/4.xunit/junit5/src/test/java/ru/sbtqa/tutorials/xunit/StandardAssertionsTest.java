package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class StandardAssertionsTest {

    private static final String MESSAGE = "информация, которая будет выведена в случае ошибки";

    @Test
    void standardAssertions() {
        assertEquals(5, 5);
        assertTrue(true, MESSAGE);
        assertFalse(false, () -> "Сообщение " + "ввиде " + "лямбды" +
                "." + MESSAGE);
    }
}
