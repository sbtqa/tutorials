package ru.sbtqa.patterns.extra.matchers;

import org.junit.Assert;

import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.everyItem;

/**
 * @author Alexey Rumyantsev
 */
public class EmailAssert {
    private List<String> strings;

    private EmailAssert(List<String> strings) {
        this.strings = strings;
    }

    public static EmailAssert assertThat(List<String> strings) {
        return new EmailAssert(strings);
    }

    public void confirmEmailRegEx() {
        Assert.assertThat("E-mail check FAIL", strings, everyItem(containsString("@")));
    }
}
