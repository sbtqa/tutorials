package ru.sbtqa.tutorials.advanced.mockito;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import java.util.Random;

import static org.junit.Assert.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_calc;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_close;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_open;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_return;

/*
С помощью Мокито нельзя мокать статические методы.
Причиной этому является то, что Мокито предпочитает ориентацию на объекты и внедрение зависимостей (DI)
над процедурным стилем программирования, который в Java реализуется именно через написание статических методов.
Код, написанный в процедурном стиле, сложно понять и сложно изменить.
Если всё-таки нам достались статические методы, работу с которыми нужно протестировать, можно
использовать другой фрэмворк - PowerMock, который расширит возможности мокито.
Стоит сразу оговориться, что сами по себе статические методы - это не обязательно плохо.
Их поведение должно быть детерменистическим и не очень сложным. Хорошим примером является метод Math.max.
Однако реальность такова, что код в процедурном стиле нам может достаться по наследству, и жить с ним
придётся.

Мокирование статических методов - один из вариантов использования PowerMock.
Можно также мокать приватные методы класса и вызовы конструкторов класса,
 но эти случаи в нашем курсе мы рассматривать не будем.

Авторы PowerMock, в документации на свой продукт явно указывают, что этот фрэймворк в первую
очередь предназначен для людей с экспертными знаниями в области модульного тестирования. Если
PowerMock попадёт в руки начинающего разработчика или тестировщика, то, возможно, это причинит
больше вреда, чем пользы.
*/

// PrepareForTest можно применять как к классу теста, так и к методу теста

/**
 * Пример 1 - это реальность, доставшийся нам интерфейс по шифрованию данных.
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Encryptor.class)
public class SomeControllerTest {
    private final SomeController someController = new SomeController();

    private byte[] document;

    @Before
    public void setUp() {
        mockStatic(Encryptor.class);

        document = new byte[1024];
        new Random().nextBytes(document);
    }

    @Test
    public void testCalculateHash() throws SomeController.UnableToCalcHashException {
//        when(hash_open(any())).thenReturn(0); // Можно не писать - 0 является значением по умолчанию

        someController.calculateHash(document);

        // Different from Mockito, always use PowerMockito.verifyStatic(Class) first
        // to start verifying behavior
        verifyStatic(Encryptor.class);
        // IMPORTANT:  Call the static method you want to verify
        hash_open(any());

        // IMPORTANT: You need to call verifyStatic(Class) per method verification,
        // so call verifyStatic(Class) again
        verifyStatic(Encryptor.class);
        // Again call the static method which is being verified
        hash_calc(anyLong(), any(), anyInt());

        verifyStatic(Encryptor.class);
        hash_return(anyLong(), any(), any());

        verifyStatic(Encryptor.class);
        hash_close(anyLong());

        // Пример мы выполнили и увидели, что PowerMock работает. Но как он это делает?
        // А делает это он путём модификации байт-кода во время исполнения тестов.
    }

    // Всё прекрасно, но очень хотелось бы проверить также, что и хэндл правильный передаётся,
    // а он возвращается внутри массива, являющегося входным для hash_open. Сам этот массив снаружи
    // передавать смысла нет - он создаётся внутри метода calculateHash.
    @Test
    public void testCalculateHash2() throws SomeController.UnableToCalcHashException {
        long handle = new Random().nextLong();

        // Мы ещё не рассматривали мокитовскую возможность по написанию кастомных ответов.
        // Эта вещь редко используется, но это как раз наш случай.

        when(hash_open(any())).then(invocation -> {
            long[] handleRef = invocation.getArgument(0);
            handleRef[0] = handle;
            return 0;
        });

        someController.calculateHash(document);

        verifyStatic(Encryptor.class);
        hash_open(any());

        verifyStatic(Encryptor.class);
        // Если используются ArgumentMatchers, то они должны быть во всех аргументах
        hash_calc(eq(handle), any(), anyInt());

        verifyStatic(Encryptor.class);
        hash_return(eq(handle), any(), any());

        verifyStatic(Encryptor.class);
        hash_close(handle);
    }

    @Test
    public void testCalculateHashFailedCalc() {
        long handle = new Random().nextLong();

        when(hash_open(any())).then(invocation -> {
            long[] handleRef = invocation.getArgument(0);
            handleRef[0] = handle;
            return 0;
        });
        when(hash_calc(eq(handle), any(), anyInt())).thenReturn(1);

        try {
            someController.calculateHash(document);
            // JUnit 4 style
            fail("Should throw UnableToCalcHashException");
        } catch (SomeController.UnableToCalcHashException e) {
            // It's OK
        }

        verifyStatic(Encryptor.class);
        hash_open(any());

        verifyStatic(Encryptor.class);
        hash_calc(eq(handle), any(), anyInt());

        // Если hash_calc вернул ошибку, то hash_return не должен вызываться
        verifyStatic(Encryptor.class, never());
        hash_return(eq(handle), any(), any());

        // Если был успешный hash_open, то должен быть успешный hash_close
        verifyStatic(Encryptor.class);
        hash_close(handle);
    }

    // Заметьте, мы ни разу не обратились к настоящей реализации Encryptor'а.
    // А ведь фактически это сложный класс, реализованный посредством нативной библиотеки.
    // Её подгрузка могла бы превратиться в огромную проблему + существенно замедлить выполнение тестов.
}