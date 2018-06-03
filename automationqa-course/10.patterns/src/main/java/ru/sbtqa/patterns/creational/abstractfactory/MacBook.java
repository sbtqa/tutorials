package ru.sbtqa.patterns.creational.abstractfactory;

/**
 * @author Alexey Rumyantsev
 */
public class MacBook implements Laptop {
    @Override
    public void turnOn() {
        System.out.println("MacBook is turned on");
    }
}
