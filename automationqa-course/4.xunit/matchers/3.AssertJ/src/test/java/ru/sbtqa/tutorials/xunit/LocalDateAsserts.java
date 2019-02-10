package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

public class LocalDateAsserts {

    @Test
    public void assertJLocalDateAssertsTest() {
        LocalDate givenLocalDate = LocalDate.of(2017, 6, 4);
        LocalDate todayDate = LocalDate.now();

        assertThat(givenLocalDate)
                .isBefore(LocalDate.of(2020, 7, 8))
                .isAfterOrEqualTo(LocalDate.of(1989, 7, 8));

        assertThat(todayDate)
                .isAfter(LocalDate.of(1989, 7, 8))
                .isToday();
    }
}
