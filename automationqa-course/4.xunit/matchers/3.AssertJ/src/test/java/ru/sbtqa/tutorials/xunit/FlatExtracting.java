package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static java.time.LocalDate.ofYearDay;
import static org.assertj.core.api.Assertions.assertThat;

public class FlatExtracting {

    @Test
    public void assertJLocalDateAssertsTest() {
        List<LocalDate> givenList = Arrays.asList(ofYearDay(2016, 5), ofYearDay(2015, 6));
        assertThat(givenList)
                .flatExtracting(LocalDate::getYear)
                .contains(2015);
    }
}
