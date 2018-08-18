package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Стандартные базовые утверждения Junit 5
 * Используем базовый класс {@link org.junit.jupiter.api.Assertions}
 * <p>
 * Внимание! - В версии Junit 5, для передачи текстового сообщения в утверждение используется последний аргумент в методе проверок
 * Также если мы сравиваем два объекта, то в метод утверждения сначала передаем ожидаемое значение, а затем фактическое.
 *
 * Примеры кода взяты: www.petrikainulainen.net/programming/testing/junit-5-tutorial-writing-assertions-with-junit-5-api/
 */
@DisplayName("Базовый функционал стандартных утверждений в Junit 5")
class BasicAssertionsTest {


    @Nested
    @DisplayName("Write assertions for booleans")
    class BooleanAssertionTest {

        @Nested
        @DisplayName("When boolean is false")
        class WhenBooleanIsFalse {

            @Test
            @DisplayName("Should be false")
            void shouldBeFalse() {
                assertFalse(false);
            }
        }
    }

    @Nested
    @DisplayName("Writing assertions for objects")
    class ObjectAssertionTest {

        @Nested
        @DisplayName("When object is null")
        class WhenObjectIsNull {

            @Test
            @DisplayName("Should be null")
            void shouldBeNull() {
                assertNull(null);
            }
        }

        @Nested
        @DisplayName("When object is not null")
        class WhenObjectIsNotNotNull {

            @Test
            @DisplayName("Should not be null")
            void shouldNotBeNull() {
                assertNotNull(new Object());
            }
        }
    }

    @Nested
    @DisplayName("When two objects are equal")
    class WhenTwoObjectsAreEqual {

        @Nested
        @DisplayName("When objects are integers")
        class WhenObjectsAreIntegers {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 9;

            @Test
            @DisplayName("Should be equal")
            void shouldBeEqual() {
                assertEquals(EXPECTED, ACTUAL);
            }
        }
    }

    @Nested
    @DisplayName("When two objects aren't equal")
    class WhenTwoObjectsAreNotEqual {

        @Nested
        @DisplayName("When objects are integers")
        class WhenObjectsAreIntegers {

            private final Integer ACTUAL = 9;
            private final Integer EXPECTED = 4;

            @Test
            @DisplayName("Should not be equal")
            void shouldNotBeEqual() {
                assertNotEquals(EXPECTED, ACTUAL);
            }
        }
    }

    @Nested
    @DisplayName("When two objects refer to the same object")
    class WhenTwoObjectsReferToSameObject {

        private final Object ACTUAL = new Object();
        private final Object EXPECTED = ACTUAL;

        @Test
        @DisplayName("Should refer to the same object")
        void shouldReferToSameObject() {
            assertSame(EXPECTED, ACTUAL);
        }
    }

    @Nested
    @DisplayName("When two objects don't refer to the same object")
    class WhenTwoObjectsDoNotReferToSameObject {

        private final Object ACTUAL = new Object();
        private final Object EXPECTED = new Object();

        @Test
        @DisplayName("Should not refer to the same object")
        void shouldNotReferToSameObject() {
            assertNotSame(EXPECTED, ACTUAL);
        }
    }
}
