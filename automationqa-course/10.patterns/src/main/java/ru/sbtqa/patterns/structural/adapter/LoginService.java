package ru.sbtqa.patterns.structural.adapter;

import ru.sbtqa.patterns.creational.factory.java8.User;

/**
 * @author Alexey Rumyantsev
 */
public interface LoginService {
    void login(User user);
}
