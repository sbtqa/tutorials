package ru.mystudy.stepdefs;

import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.BeforeStep;

public class Hooks {

    @Before(order = 100)
    public void before0() {
        System.out.println("before 1 scenario");
    }

    @Before
    public void before() {
        System.out.println("before 2 scenario");
    }

    @BeforeStep(order = 100)
    public void beforeStep0() {
        System.out.println("before 1 step");
    }

    @BeforeStep
    public void beforeStep() {
        System.out.println("before 2 step");
    }

    @AfterStep(order = 100)
    public void afterStep0() {
        System.out.println("after 1 step");
    }

    @AfterStep
    public void afterStep() {
        System.out.println("after 2 step");
    }

    @After(order = 100)
    public void after0() {
        System.out.println("after 1 scenario");
    }

    @After
    public void after() {
        System.out.println("after 2 scenario");
    }
}
