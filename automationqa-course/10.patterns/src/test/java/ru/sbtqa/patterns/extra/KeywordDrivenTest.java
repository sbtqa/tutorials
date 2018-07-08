package ru.sbtqa.patterns.extra;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        glue = {"ru.sbtqa.tag.stepdefs"},
        features = {"src/test/resources/features/"},
        tags = "@Notebooks",
        format = {"pretty"})
public class KeywordDrivenTest {
}
