package ru.mystudy;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@CucumberOptions(
        glue = "ru.mystudy.stepdefs",
        features = "src/test/resources/features",
        tags = "@success, @fail"
)
//public class RunTest extends AbstractTestNGCucumberTests { // для запуска через TestNG
@RunWith(Cucumber.class) // для запуска через JUnit
public class RunTest {
}
