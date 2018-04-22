package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Samples for stubbing.
 */
class StubbingListsTest {
    // В данном контексте SuppressWarnings допустим, но только для unchecked - мы создаём mockedList, который будет хранить только Integer'ы
    @SuppressWarnings("unchecked")
    private final List<Integer> mockedList = Mockito.mock(List.class);
    // Можно мокать не только интерфейсы, как в этом примере, но и конкретные реализации

    @Test
    void testStubbingMethodWithReturnValue() {
        // Создаётся заглушка - если у объекта mockedList будет вызван метод get с аргументом 0, то необходимо вернуть 1
        Mockito.when(mockedList.get(0)).thenReturn(1);
        // Создаётся заглушка - если у объекта mockedList будет вызван метод get с аргументом 1, то необходимо вернуть 2
        Mockito.when(mockedList.get(1)).thenReturn(2);
        // Создаётся заглушка - если у объекта mockedList будет вызван метод get с аргументом 2, то бросить исключение RuntimeException
        Mockito.when(mockedList.get(2)).thenThrow(new RuntimeException());
        // Создаётся заглушка - если у объекта mockedList будет вызван метод size, то необходимо вернуть 1000
        Mockito.when(mockedList.size()).thenReturn(1000);

        // Assert'ы здесь с точки зрения простого отображения ожидаемого и фактического результата, так как проверять mockito не является нашей целью

        // Вернётся 1, так как была создана заглушка для вызова метода get cо значением аргумента 0
        assertEquals(Integer.valueOf(1), mockedList.get(0));
        // Вернётся 2, так как была создана заглушка для вызова метода get cо значением аргумента 1
        assertEquals(Integer.valueOf(2), mockedList.get(1));
        // Будет брошено исключение RuntimeException - была создана соответствующая заглушка
        assertThrows(RuntimeException.class, () -> mockedList.get(2));
        // Вернётся 1000 - для вызова метода size была создана соответствующая заглушка
        assertEquals(1000, mockedList.size());

        // Не был задан стаб для элемента с 3-м индексом - вернётся null, так как возвращаемый тип этого метода - ссылка
        assertEquals(null, mockedList.get(3));
        // Метод isEmpty без заглушки - вернётся false, так как возвращаемый тип этого метода - boolean
        assertEquals(false, mockedList.isEmpty());
        // Метод lastIndexOf без заглушки - вернётся 0, так как возвращаемый тип этого метода - int
        assertEquals(0, mockedList.lastIndexOf(2));
        // В случае, когда возвращается коллекция, будет возвращён не null, а пустая коллекция
        assertTrue(mockedList.subList(0, 1).isEmpty());
    }

    @Test
    void testStubbingMethodsWithoutReturnValue() {
        // Если у метода нет возвращаемого значения (void), то придётся в начале написать doThrow, чтобы реализовать DSL конструкцию
        Mockito.doThrow(new RuntimeException()).when(mockedList).clear();

        assertThrows(RuntimeException.class, mockedList::clear);
    }

    @Test
    void testMultipleInvocationsExample1() {
        Mockito.when(mockedList.get(0)).thenReturn(1);

        // Если один раз заглушку написали, то она будет постоянно возвращаться

        assertEquals(Integer.valueOf(1), mockedList.get(0));
        assertEquals(Integer.valueOf(1), mockedList.get(0));
        assertEquals(Integer.valueOf(1), mockedList.get(0));
        assertEquals(Integer.valueOf(1), mockedList.get(0));
        assertEquals(Integer.valueOf(1), mockedList.get(0));
    }

    @Test
    void testMultipleInvocationsExample2() {
//        Mockito.when(mockedList.get(0)).thenReturn(1).thenReturn(10).thenReturn(100);
        Mockito.when(mockedList.get(0)).thenReturn(1, 10, 100); // Сокращённая запись того же

        // Если несколько раз задали возвращаемое значение, то пойдут по очереди.
        // Если возвращаемых значений не останется, то будет возвращаться последнее.

        assertEquals(Integer.valueOf(1), mockedList.get(0));
        assertEquals(Integer.valueOf(10), mockedList.get(0));
        assertEquals(Integer.valueOf(100), mockedList.get(0));
        assertEquals(Integer.valueOf(100), mockedList.get(0));
        assertEquals(Integer.valueOf(100), mockedList.get(0));
    }

    @Test
    void testMultipleInvocationsExample3() {
        // + thenThrow не обязательно нужен объект, достаточно указать какого типа должно быть исключение
        Mockito.when(mockedList.get(0)).thenReturn(1).thenThrow(RuntimeException.class);

        // Если несколько раз задали возвращаемое значение, то пойдут по очереди.
        // Если возвращаемых значений не останется, то будет возвращаться последнее.

        assertEquals(Integer.valueOf(1), mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
        assertThrows(RuntimeException.class, () -> mockedList.get(0));
    }
}
