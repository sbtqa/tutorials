package ru.sbtqa.tutorials.advanced.mockito;

import static java.lang.System.arraycopy;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_calc;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_close;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_open;
import static ru.sbtqa.tutorials.advanced.mockito.Encryptor.hash_return;

/**
 *
 */
public class SomeController {
    /**
     * Максимальная длина выходного буфера.
     */
    private final static int MAX_LENGTH = 0xffff;

    /**
     * Пример с реализацией получения хэша документа.
     *
     * @param document массив байт, являющийся представлением документа
     * @return вычисленное значение хэша
     */
    public byte[] calculateHash(byte[] document) throws UnableToCalcHashException {
        // Инициируем процедуру вычисления хэша, получаем хэндл
        long[] handleRef = new long[1];
        int errorCode = hash_open(handleRef);
        if (errorCode != 0) {
            throw new UnableToCalcHashException(errorCode);
        }
        long handle = handleRef[0];

        try {
            // Производим вычисление хэша
            errorCode = hash_calc(handle, document, document.length);
            if (errorCode != 0) {
                throw new UnableToCalcHashException(errorCode);
            }

            // Возвращаем результат выполнения вычисления хэша
            byte[] buffer = new byte[MAX_LENGTH];
            int[] bufferLengthRef = {MAX_LENGTH};
            errorCode = hash_return(handle, buffer, bufferLengthRef);
            if (errorCode != 0) {
                throw new UnableToCalcHashException(errorCode);
            }
            int bufferLength = bufferLengthRef[0];

            byte[] hash = new byte[bufferLength];
            arraycopy(buffer, 0, hash, 0, bufferLength);
            return hash;
        } finally {
            // Завершаем процедуру вычисления, закрываем открытые ресурсы, освобождаем хэндл
            errorCode = hash_close(handle);
            if (errorCode != 0) {
                System.err.println("Unable to close document hash calculator handle, error code: " + errorCode);
            }
        }
    }

    public static final class UnableToCalcHashException extends Exception {
        public UnableToCalcHashException(int errorCode) {
            super("Unable to calculate document hash, error code: " + errorCode);
        }
    }
}
