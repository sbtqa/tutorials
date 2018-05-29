package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Value. Immutable.
 */
//final class Point {
class Point {
    private final int x;
    private final int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    final public int getY() {
        return y;
    }
}

/**
 * Mockito cannot mock final classes. Mockito cannot mock final methods - their real behavior is
 * executed without any exception. Mockito cannot warn you about mocking final methods so be
 * vigilant.
 * Mockito now (since 2.1.0) offers an Incubating, optional support for mocking final classes and methods.
 * Раз Incubating, то в рамках этого курса мы это рассматривать не будем.
 *
 * + You can not mock or stub private methods. From the standpoint of testing... private methods
 * don't exist.
 */
class DontMockFinalTest {
    private final Point point = mock(Point.class);

    @Test
    void testPointGetX() {
        when(point.getX()).thenReturn(10);

        assertEquals(10, point.getX());
    }

    @Test
    @Disabled
    void testPointGetY() {
        when(point.getY()).thenReturn(20);

        assertEquals(20, point.getY());
    }
}
