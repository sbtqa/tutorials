package ru.sbtqa.patterns.creational.pool;

public class Resource {
    Resource() {
        System.out.println("open resource");
    }

    public Resource operation1() {
        System.out.println("Operation 1");
        return this;
    }

    public Resource operation2() {
        System.out.println("Operation 2");
        return this;
    }

    void close() {
        System.out.println("free resource");
    }
}
