package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;

import java.util.List;

import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

/**
 * Samples for stubbing.
 */
class StubbingListsTest {
    // В данном контексте SuppressWarnings допустим, но только для unchecked - мы создаём mockedList, который будет хранить только Integer'ы
    @SuppressWarnings("unchecked")
    private final List<Integer> mockedList = mock(List.class);
    // Можно мокать не только интерфейсы, как в этом примере, но и конкретные реализации

    @Test
    void testStubbingMethodWithReturnValue() {
        // Создаётся заглушка - если у объекта mockedList будет вызван метод get с аргументом 1, то необходимо вернуть 2
        when(mockedList.get(1)).thenReturn(2);
        // Создаётся заглушка - если у объекта mockedList будет вызван метод get с аргументом 0, то необходимо вернуть 1
        when(mockedList.get(0)).thenReturn(1);
        // Создаётся заглушка - если у объекта mockedList будет вызван метод get с аргументом 2, то бросить исключение RuntimeException
        when(mockedList.get(2)).thenThrow(new RuntimeException());
        // Создаётся заглушка - если у объекта mockedList будет вызван метод size, то необходимо вернуть 1000
        when(mockedList.size()).thenReturn(1000);

        // Assert'ы здесь с точки зрения простого отображения ожидаемого и фактического результата, так как проверять mockito не является нашей целью
        // Не путайте в assert'ах местами значения - обратите внимание, слева expected, справа actual.

        assertEquals(valueOf(1), mockedList.get(0)
                , "Возвращено значение заглушки для вызова метода get cо значением аргумента 0");
        assertEquals(valueOf(2), mockedList.get(1)
                , "Возвращено значение заглушки для вызова метода get cо значением аргумента 1");
        assertThrows(RuntimeException.class, () -> mockedList.get(2)
                , "Брошено исключение RuntimeException, которое запрограммировано для вызова метода get cо значением аргумента 2");
        assertEquals(1000, mockedList.size()
                , "Возвращено значение заглушки для вызова метода size");

        assertNull(mockedList.get(3)
                , "Не был задан стаб для элемента с 3-м индексом - вернётся null, так как возвращаемый тип этого метода - ссылка");
        assertFalse(mockedList.isEmpty()
                , "Метод isEmpty без заглушки - вернётся false, так как возвращаемый тип этого метода - boolean");
        assertEquals(0, mockedList.lastIndexOf(2)
                , "Метод lastIndexOf без заглушки - вернётся 0, так как возвращаемый тип этого метода - int");
        assertTrue(mockedList.subList(0, 1).isEmpty()
                , "В случае, когда возвращается коллекция, будет возвращён не null, а пустая коллекция");
    }

    @Test
    void testStubbingMethodsWithoutReturnValue() {
        // Если у метода нет возвращаемого значения (void), то придётся в начале написать doThrow, чтобы реализовать DSL конструкцию
        doThrow(new RuntimeException()).when(mockedList).clear();

        assertThrows(RuntimeException.class, mockedList::clear);
    }

    @Test
    void testMultipleInvocationsExample1() {
        when(mockedList.get(0)).thenReturn(1);

        // Если один раз заглушку написали, то она будет постоянно возвращаться

        assertEquals(valueOf(1), mockedList.get(0));
        assertEquals(valueOf(1), mockedList.get(0));
        assertEquals(valueOf(1), mockedList.get(0));
        assertEquals(valueOf(1), mockedList.get(0));
        assertEquals(valueOf(1), mockedList.get(0));
    }

    @Test
    void testMultipleInvocationsExample2() {
//        when(mockedList.get(0)).thenReturn(1).thenReturn(10).thenReturn(100);
        when(mockedList.get(0)).thenReturn(1, 10, 100); // Сокращённая запись того же

        // Если несколько раз задали возвращаемое значение, то пойдут по очереди.
        // Если возвращаемых значений не останется, то будет возвращаться последнее.

        assertEquals(valueOf(1), mockedList.get(0));
        assertEquals(valueOf(10), mockedList.get(0));
        assertEquals(valueOf(100), mockedList.get(0));
        assertEquals(valueOf(100), mockedList.get(0));
        assertEquals(valueOf(100), mockedList.get(0));
    }

    @Test
    void testMultipleInvocationsExample3() {
        // + thenThrow не обязательно нужен объект, достаточно указать какого типа должно быть исключение
        when(mockedList.get(0)).thenReturn(1).thenThrow(RuntimeException.class);

        // Если несколько раз задали возвращаемое значение, то пойдут по очереди.
        // Если возвращаемых значений не останется, то будет возвращаться последнее.

        assertEquals(valueOf(1), mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
    }
}
