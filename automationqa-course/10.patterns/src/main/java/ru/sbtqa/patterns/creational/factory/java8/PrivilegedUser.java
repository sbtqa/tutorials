package ru.sbtqa.patterns.creational.factory.java8;

/**
 * @author Alexey Rumyantsev
 */
public class PrivilegedUser extends User {
    @Override
    public void operate() {
        System.out.println("Privileged enters");
    }
}
