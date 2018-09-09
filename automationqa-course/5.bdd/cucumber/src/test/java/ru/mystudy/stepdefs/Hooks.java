package ru.mystudy.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;

public class Hooks {

    @Before
    public void before() {
        System.out.println("before scenario");
    }

    @AfterStep()
    public void afterStep() {
        System.out.println("afterstep");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("beforestep");
    }

    @After
    public void after() {
        System.out.println("after scenarion");
    }
}
