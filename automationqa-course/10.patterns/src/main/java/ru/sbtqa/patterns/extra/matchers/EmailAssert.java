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

    public static EmailAssert assertThat(List<String> strings) {
        return new EmailAssert(strings);
    }

    private EmailAssert(List<String> strings) {
        this.strings = strings;
    }

    public void confirmEmailRegex() {
        Assert.assertThat("e-mail regex fail", strings, everyItem(containsString("@")));
    }
}
