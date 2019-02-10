package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class Matches {

    @Test
    public void assertMatchesTest() {
        String givenString = "";
        assertThat(givenString)
                .matches(String::isEmpty);
    }
}
