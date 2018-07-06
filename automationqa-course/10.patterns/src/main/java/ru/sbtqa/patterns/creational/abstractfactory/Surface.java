package ru.sbtqa.patterns.creational.abstractfactory;

/**
 * @author Alexey Rumyantsev
 */
public class Surface implements Laptop {
    @Override
    public void turnOn() {
        System.out.println("Microsoft Surface is turned on");
    }
}
