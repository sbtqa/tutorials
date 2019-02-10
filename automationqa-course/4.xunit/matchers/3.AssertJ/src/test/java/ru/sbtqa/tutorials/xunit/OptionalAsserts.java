package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

public class OptionalAsserts {

    @Test
    public void assertJOptionalAssertsTest() {
        Optional<String> checkedValue = Optional.of("Volga");
        assertThat(checkedValue)
                .isPresent()
                .hasValue("Volga");
    }
}
