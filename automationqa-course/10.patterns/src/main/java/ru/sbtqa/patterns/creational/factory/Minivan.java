package ru.sbtqa.patterns.creational.factory;

/**
 * @author Alexey Rumyantsev
 */
public class Minivan implements Car {
    private int capacity = 10;

    @Override
    public void accelerate() {
        System.out.println("Minivan accelerates");
    }

    @Override
    public void brake() {
        System.out.println("Minivan brakes");
    }

    @Override
    public void turn() {
        System.out.println("Minivan is turning...");
    }
}
