package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class AssertJIntroductionInAsserts {

    private static List<String> sovietCars = Arrays.asList("Volga", "Moskvitch", "Lada", "Zaporojets");

    @Test
    public void assertJIntroductionInAssertsTest() {
        assertThat(sovietCars.get(0)).isEqualTo("Volga");
        assertThat(sovietCars.get(0)).isNotEqualTo("Mercedes");

        assertThat(sovietCars.get(1))
                .startsWith("Mosk")
                .endsWith("vitch")
                .isEqualToIgnoringCase("moskvitch");

        assertThat(sovietCars)
                .hasSize(4)
                .contains("Volga", "Moskvitch", "Zaporojets")
                .doesNotContain("Audi");
    }
}
