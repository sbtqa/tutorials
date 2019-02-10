package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.function.Predicate;

import static org.assertj.core.api.Assertions.assertThat;

public class PredicateAsserts {

    @Test
    public void assertJPredicateAssertsTest() {
        Predicate<Integer> simplePredicate = i -> i > 5;
        assertThat(simplePredicate)
                .accepts(6, 7)
                .rejects(5, 4)
                .acceptsAll(Arrays.asList(6, 7))
                .rejectsAll(Arrays.asList(5, 4));
    }
}
