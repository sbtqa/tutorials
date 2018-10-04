package ru.mystudy.stepdefs;

import cucumber.api.java.ru.Допустим;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomParameterTypeStepDefinitions {
    @Допустим("передадим в метод дату {localdate}")
    public void передадим_в_метод_дату(LocalDate localdate) {
        System.out.println(localdate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
    }
}
