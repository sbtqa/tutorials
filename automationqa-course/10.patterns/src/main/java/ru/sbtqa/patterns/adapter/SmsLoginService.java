package ru.sbtqa.patterns.adapter;

import ru.sbtqa.patterns.creational.factory.java8.User;

/**
 * @author Alexey Rumyantsev
 */
public class SmsLoginService implements LoginService {

    @Override
    public void login(User user) {
        System.out.println("Call SmsLoginService login");
        SmsLoginUtils.loginBySms(user);
    }
}
