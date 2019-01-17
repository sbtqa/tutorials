package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

import static java.time.Duration.ofMillis;
import static org.junit.jupiter.api.Assertions.assertTimeoutPreemptively;

class TimeoutWithPreemptiveTerminationTest {

    @Test
    void timeoutExceededWithPreemptiveTermination() {
        assertTimeoutPreemptively(ofMillis(10), () -> {
            TimeUnit.MILLISECONDS.sleep(100);
        }, () -> "Не дожидаемся выполнения операции и сразу прерываем ее");
    }
}