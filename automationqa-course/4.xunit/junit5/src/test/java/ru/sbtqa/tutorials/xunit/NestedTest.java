package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class NestedTest {

    @BeforeEach
    void setUp1() {
        System.out.println("Setup 1");
    }

    @AfterEach
    void tearDown() {
        System.out.println("tearDown 1");
    }

    @Test
    void topTest() {
        System.out.println("Test 1");
    }


    @Nested
    class InnerClass1 {
        @BeforeEach
        void setUp2() {
            System.out.println("Setup 2");
        }

        @Test
        void innerTest1() {
            System.out.println("Test 2");
        }

        @Nested
        class InnerClass2 {
            @Test
            void innerTest2() {
                System.out.println("Test 3");
            }
        }
    }
}
