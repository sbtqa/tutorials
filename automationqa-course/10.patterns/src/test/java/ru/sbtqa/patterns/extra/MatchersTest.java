package ru.sbtqa.patterns.extra;

import org.junit.Assert;
import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.extra.matchers.EmailAssert;

import java.util.ArrayList;
import java.util.List;

import static ru.sbtqa.patterns.extra.matchers.EmailAssert.assertThat;


/**
 * @author Alexey Rumyantsev
 */
class MatchersTest {

    @Test
    void codeSmellTest() {
        List<String> strings = new ArrayList<>();
        strings.add("abcmail.ru");
        strings.add("abc@google.com");
        strings.add("abc@zxy.org");

        strings.forEach(s -> Assert.assertTrue(s + " is invalid", s.contains("@")));

    }

    @Test
    void matcherTest() {
        List<String> strings = new ArrayList<>();
        strings.add("abcmail.ru");
        strings.add("abc@google.com");
        strings.add("abc@zxy.org");

        assertThat(strings).confirmEmailRegEx();
    }
}
