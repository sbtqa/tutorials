package ru.mystudy.stepdefs;

import cucumber.api.java.ru.Дано;
import cucumber.api.java.ru.Когда;

public class FirstStepDefinitions {
    @Дано("^на счете пользователя имеется (\\d+) рублей$")
    public void наСчетеПользователяИмеетсяРублей(int arg0) {
        System.out.println("наСчетеПользователяИмеетсяРублей " + arg0);
    }

    @Когда("^пользователь снимает со счета (\\d+) рублей$")
    public void пользовательСнимаетСоСчетаРублей(int arg0) {
        System.out.println("пользовательСнимаетСоСчетаРублей " + arg0);
    }
}
