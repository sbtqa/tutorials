package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Satisfies {

    @Test
    public void assertJSatisfiesTest() {
        String givenString = "someString";
        assertThat(givenString)
                .satisfies(s -> {
                    assertThat(s).isNotEmpty();
                    assertThat(s).hasSize(10);
                });
    }
}
