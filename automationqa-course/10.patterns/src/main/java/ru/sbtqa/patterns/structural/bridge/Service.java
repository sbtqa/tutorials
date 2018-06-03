package ru.sbtqa.patterns.structural.bridge;

public abstract class Service {
    protected DataSource dataSource;

    public abstract void run();
}
