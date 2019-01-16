package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.io.Serializable;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJClassAsserts {

    @Test
    public void assertJClassAssertsTest() {
        assertThat(Serializable.class).isInterface();
        assertThat(Predicate.class).hasAnnotation(FunctionalInterface.class);
    }
}
