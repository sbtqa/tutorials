package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJIterableAsserts {

    private static List<String> sovietCars = Arrays.asList("Volga", "Moskvitch", "Lada", "Zaporojets");

    @Test
    public void assertJIterableAssertsListNotEmptyTest() {
        assertThat(sovietCars).isNotEmpty();
    }

    @Test
    public void assertJIterableAssertsListContainsValueTest() {
        assertThat(sovietCars).contains("Zaporojets");
    }

    @Test
    public void assertJIterableAssertsStartWithTest() {
        assertThat(sovietCars).startsWith("Volga");
    }

    @Test
    public void assertJIterableAssertsComplexAssertionTest() {
        assertThat(sovietCars)
                .isNotEmpty()
                .doesNotContain("Volvo")
                .endsWith("Zaporojets");
    }

}
