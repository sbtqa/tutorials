package ru.sbtqa.patterns.structural.bridge;

import ru.sbtqa.patterns.structural.bridge.data.DataSource;

public abstract class Service {

    protected DataSource dataSource;

    public Service(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public abstract void run();
}
