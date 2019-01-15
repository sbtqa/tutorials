package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

class GroupedAssertionsTest {

    @Test
    void groupedAssertions() {
        Customer customer = new Customer("Artem", "Sokovets");
        assertAll("Проверка имя и фамилии", //Задан лейбл, т.к методов assertAll может быть несколько
                () -> assertEquals("Artem", customer.name),
                () -> assertEquals("Sokovets", customer.lastName),
                () -> assertEquals("Artem Sokovets", "ArtSok"));
    }

    private final static class Customer {
        private final String name;
        private final String lastName;

        Customer(String name, String lastName) {
            this.name = name;
            this.lastName = lastName;
        }
    }
}
