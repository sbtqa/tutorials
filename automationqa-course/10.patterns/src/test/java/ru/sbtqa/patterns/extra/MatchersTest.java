package ru.sbtqa.patterns.extra;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertTrue;
import static ru.sbtqa.patterns.extra.matchers.EmailAssert.assertThat;

/**
 * @author Alexey Rumyantsev
 */
public class MatchersTest {

    @Test
    public void codeSmellTest(){
        List<String> strings = new ArrayList<>();
        strings.add("abc@mail.ru");
        strings.add("abc@google.com");
        strings.add("abc@zxy.org");

        for (String s: strings) {
            assertTrue(s.contains("@"));
        }
    }

    @Test
    public void matcherTest(){
        List<String> strings = new ArrayList<>();
        strings.add("abc@mail.ru");
        strings.add("abc@google.com");
        strings.add("abc@zxy.org");

        assertThat(strings).confirmEmailRegex();
    }
}
