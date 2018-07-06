package ru.sbtqa.patterns.extra;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, format = {"pretty"},
        glue = {"ru.sbtqa.tag.stepdefs"},
        features = {"src/test/resources/features/"})
public class KeywordDrivenTest {}
