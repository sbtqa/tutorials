package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.Objects;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJBooleanAsserts {

    @Test
    public void assertJBooleanAssertsTest() {
        assertThat(Objects.isNull(null)).isTrue();
        assertThat("AssertJ".isEmpty()).isFalse();
    }

}
