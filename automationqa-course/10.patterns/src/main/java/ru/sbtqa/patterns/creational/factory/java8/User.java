package ru.sbtqa.patterns.creational.factory.java8;

/**
 * @author Alexey Rumyantsev
 */
public abstract class User {
    public abstract void operate();

    public enum UserType {
        NEWBIE, AVERAGE, PRIVILEGED
    }
}
