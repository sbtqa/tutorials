package ru.mystudy.stepdefs;

import cucumber.api.java.ru.ƒано;
import cucumber.api.java.ru. огда;
import cucumber.api.java.ru.“огда;

public class StepDefinitions {
    @ƒано("^на счете пользовател€ имеетс€ (\\d+) рублей$")
    public void на—четеѕользовател€»меетс€–ублей(int arg0) {
        System.out.println("на—четеѕользовател€»меетс€–ублей " + arg0);
    }

    @ огда("^пользователь снимает со счета (\\d+) рублей$")
    public void пользователь—нимает—о—чета–ублей(int arg0) {
        System.out.println("пользователь—нимает—о—чета–ублей " + arg0);
    }

    @“огда("^по€вл€етс€ предупреждение \"([^\"]*)\"$")
    public void по€вл€етс€ѕредупреждение(String arg0) {
        System.out.println("по€вл€етс€ѕредупреждение " + arg0);
    }
}
