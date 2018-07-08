package ru.sbtqa.patterns.structural.bridge;

import ru.sbtqa.patterns.structural.bridge.data.DataSource;

public class LoginService extends Service {
    public LoginService(DataSource dataSource) {
        super(dataSource);
    }

    @Override
    public void run() {
        System.out.println("Getting parameter for LoginService");
        System.out.println(dataSource.getParameter("userID"));
    }
}
