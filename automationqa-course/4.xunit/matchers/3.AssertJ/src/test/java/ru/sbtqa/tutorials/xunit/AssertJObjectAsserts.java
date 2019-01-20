package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AssertJObjectAsserts {

    @Test
    public void assertJObjectAssertsIsEqualToTest() {
        Person jack = new Person("Jack", 15);
        Person anotherJack = new Person("Jack", 15);
        assertThat(jack).isEqualTo(anotherJack);
    }

    @Test
    public void assertJObjectAssertsIsEqualToComparingFieldByFieldTest() {
        Person jack = new Person("Jack", 15);
        Person anotherJack = new Person("Jack", 15);
        assertThat(jack).isEqualToComparingFieldByField(anotherJack);
    }

    private class Person {
        private String name;
        private int age;

        public Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
