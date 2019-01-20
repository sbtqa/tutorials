package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJDescribeAsserts {

    @Test
    public void assertJDescribeAssertTest() {
        Person person = new Person("Daniel", 20);
        assertThat(person.age)
                .as("%s's age", person.name)
                .isEqualTo(100);
    }

    private class Person {
        private String name;
        private int age;

        private Person(String name, int age) {
            this.name = name;
            this.age = age;
        }
    }
}
