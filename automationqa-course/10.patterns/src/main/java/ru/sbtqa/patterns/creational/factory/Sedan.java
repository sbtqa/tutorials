package ru.sbtqa.patterns.creational.factory;

/**
 * @author Alexey Rumyantsev
 */
public class Sedan implements Car {
    private int capacity = 5;
    private int id;

    public Sedan(int id) {

        this.id = id;
    }

    @Override
    public void accelerate() {
        System.out.println("Sedan accelerates");
    }

    @Override
    public void brake() {
        System.out.println("Sedan brakes");
    }

    @Override
    public void turn() {
        System.out.println("Sedan is turning...");
    }
}
