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
import static org.mockito.ArgumentMatchers.same;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;
import static org.powermock.api.mockito.PowerMockito.verifyStatic;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_calc;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_close;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_open;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_return;

// PrepareForTest можно применять как к классу теста, так и к методу теста
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
        // Если используются ArgumentMatchers, то они должны быть во всех аргументах
        hash_calc(anyLong(), same(document), eq(document.length));

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
        // Эта вещь редко используется, но это как раз наш случай - нам нужно задать хэндл.

        when(hash_open(any())).then(invocation -> {
            long[] handleRef = invocation.getArgument(0);
            handleRef[0] = handle;
            return 0;
        });

        someController.calculateHash(document);

        verifyStatic(Encryptor.class);
        hash_open(any());

        verifyStatic(Encryptor.class);
        hash_calc(handle, document, document.length);

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
            // It's OK - UnableToCalcHashException должен был проброситься
        }

        verifyStatic(Encryptor.class);
        hash_open(any());

        verifyStatic(Encryptor.class);
        hash_calc(handle, document, document.length);

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