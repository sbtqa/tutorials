package ru.sbtqa.patterns.structural.bridge;

public class LoginService extends Service {

    public LoginService(DataSource source) {
        dataSource = source;
    }

    @Override
    public void run() {
        System.out.println("Getting parameter for LoginService");
        System.out.println(dataSource.getParameter("userId"));
    }
}
