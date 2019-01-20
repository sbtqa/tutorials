package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.io.InputStream;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJInputStreamAsserts {

    @Test
    public void assertJInputStreamAssertsTest() {
        InputStream is = AssertJInputStreamAsserts.class.getResourceAsStream("/file.txt");
        assertThat(is).hasContent("Hello, AssertJ!");
    }
}
