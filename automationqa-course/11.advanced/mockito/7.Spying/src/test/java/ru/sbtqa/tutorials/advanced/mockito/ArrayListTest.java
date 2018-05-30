package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Integer.valueOf;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Spying examples.
 */
class ArrayListTest {
    private final ArrayList<Integer> arrayList = new ArrayList<>();

    // Другой вариант - использовать аннотацию Spy (подключение аналогично аннотации Mock)
    /**
     * {@code PaymentBankAccount} spy.
     */
    private final List<Integer> arrayListSpy = spy(arrayList);

    // Шпион в мокито делается для реальных экземпляров классов.
    // Если на метод не была задана заглушка, то будет вызван настоящий метод класса

    @Test
    void test1() {
        arrayListSpy.add(1);
        arrayListSpy.add(2);
        arrayListSpy.add(3);

        assertEquals(3, arrayListSpy.size());
    }

    @Test
    void test2() {
        arrayListSpy.add(1);
        arrayListSpy.add(2);
        arrayListSpy.add(3);

        when(arrayListSpy.size()).thenReturn(1000);

        assertEquals(1000, arrayListSpy.size());

        when(arrayListSpy.size()).thenCallRealMethod();
        assertEquals(3, arrayListSpy.size());
    }

    @Test
    void test3() {
        arrayListSpy.add(1);
        arrayListSpy.add(2);
        arrayListSpy.add(3);

        when(arrayListSpy.get(1)).thenReturn(20);

        assertEquals(valueOf(1), arrayListSpy.get(0));
        assertEquals(valueOf(20), arrayListSpy.get(1));
        assertEquals(valueOf(3), arrayListSpy.get(2));
        assertThrows(IndexOutOfBoundsException.class, () -> arrayListSpy.get(3));
        verify(arrayListSpy).add(2);

        // Мокито не работает напрямую с объектом, шпионом которого он является. Мокито работает с его копией.
        // Поэтому взаимодействие с arrayListSpy никак не повлияет на arrayList
        assertEquals(0, arrayList.size());

        // Вообще, есть возможность ещё делать Real partial mocks и моки абстрактных классов - это
        // редко применяется в обычной жизни.
    }


    @Test
    void test4() {
        // Так (написать заглушку на get(1) для пустого списка (на данный момент он пустой)) сделать не получится.
        // Особенность в том, что реальный вызов get на пустом списке вернёт исключение IndexOutOfBoundsException
//        when(arrayListSpy.get(1)).thenReturn(20);

        // Вместо этого нужно делать так
        doReturn(20).when(arrayListSpy).get(1);

        assertEquals(valueOf(20), arrayListSpy.get(1));
    }
}
