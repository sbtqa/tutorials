package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.HashMap;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.entry;

public class AssertJMapAsserts {
    private static Map<String, String> ladaCars = new HashMap<>();
    static {
        ladaCars.put("ВАЗ-1119", "Калина");
        ladaCars.put("ВАЗ-2170", "Приора");
        ladaCars.put("ВАЗ-2190", "Гранта");
    }

    @Test
    public void assertJNumericAsserts() {
        assertThat(ladaCars)
                .isNotEmpty()
                .containsKey("ВАЗ-1119")
                .doesNotContainKeys("ВАЗ-2110")
                .contains(entry("ВАЗ-2170", "Приора"));
    }
}
