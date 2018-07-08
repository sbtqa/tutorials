package ru.sbtqa.patterns.structural.bridge;

import ru.sbtqa.patterns.structural.bridge.data.DataSource;

public class RequestService extends Service {
    public RequestService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void run() {
        System.out.println("Getting parameter for RequestService");
    }
}
