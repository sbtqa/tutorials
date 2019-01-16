package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJCharAsserts {

    @Test
    public void assertJCharAssertsTest() {
        assertThat('x')
                .isNotEqualTo('a')
                .inUnicode()
                .isGreaterThanOrEqualTo('b')
                .isLowerCase();
    }
}
