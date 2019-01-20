package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.withPrecision;

public class AssertJNumericAsserts {

    @Test
    public void assertJNumericAsserts() {
        assertThat(5.1).isEqualTo(5, withPrecision(1d));
    }
}
