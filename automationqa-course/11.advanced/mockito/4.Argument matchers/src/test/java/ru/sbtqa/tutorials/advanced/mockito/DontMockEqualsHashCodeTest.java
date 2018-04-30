package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

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

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Point point = (Point) o;

        if (x != point.x) return false;
        return y == point.y;
    }

    @Override
    public int hashCode() {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}

/**
 * You cannot mock equals(), hashCode() with Mockito. Firstly, you should not mock those methods.
 * Secondly, Mockito defines and depends upon a specific implementation of these methods. Redefining
 * them might break Mockito.
 */
class DontMockEqualsHashCodeTest {
    @Test
    void testPointMockEquals() {
        Point zeroPoint = new Point(0, 0);
        Point point = mock(Point.class);
        when(point.equals(zeroPoint)).thenReturn(true);

        assertTrue(point.equals(zeroPoint));
    }
}
