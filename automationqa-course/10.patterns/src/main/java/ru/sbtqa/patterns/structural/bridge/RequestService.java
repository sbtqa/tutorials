package ru.sbtqa.patterns.structural.bridge;

public class RequestService extends Service {
    public RequestService(DataSource source) {
        dataSource=source;
    }

    @Override
    public void run() {
        System.out.println("Getting parameter for RequestService");
        System.out.println(dataSource.getParameter("requestId"));
    }
}
