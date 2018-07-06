package ru.sbtqa.patterns.creational.factory;

/**
 * @author Alexey Rumyantsev
 */
public class Smart implements Car {
    private int capacity = 2;

    @Override
    public void accelerate() {
        System.out.println("Smart accelerates");
    }

    @Override
    public void brake() {
        System.out.println("Smart brakes");
    }

    @Override
    public void turn() {
        System.out.println("Smart is turning...");
    }
}
