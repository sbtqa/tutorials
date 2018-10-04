package ru.mystudy.stepdefs;

import cucumber.api.java.ru.Допустим;
import ru.mystudy.pojo.User;

import java.util.List;

public class CustomDataTableParameterTypeStepDefinitions {
    @Допустим("у нас есть пользователи")
    public void у_нас_есть_пользователи(List<User> users) {
        System.out.println(users);
    }
}
