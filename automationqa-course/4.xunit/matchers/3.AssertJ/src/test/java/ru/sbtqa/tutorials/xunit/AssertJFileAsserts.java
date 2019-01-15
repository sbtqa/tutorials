package ru.sbtqa.tutorials.xunit;

import org.junit.jupiter.api.Test;

import java.io.File;

import static org.assertj.core.api.Assertions.assertThat;

public class AssertJFileAsserts {

    @Test
    public void assertJFileAssertsTest() {
        assertThat(new File("src/test/resources/file.txt"))
                .exists()
                .isFile()
                .canRead()
                .canWrite();
    }
}
