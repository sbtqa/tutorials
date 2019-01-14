package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class ExceptionTest {

    /**
     * Делить на 0 нельзя!
     * @return {@link ArithmeticException}
     */
    private int divide() {
        return 3/0;
    }

    @Test
    void exceptionTesting() {
        Throwable exception = assertThrows(ArithmeticException.class, this::divide);
        assertEquals(exception.getMessage(), "/ by zero");
    }
}
