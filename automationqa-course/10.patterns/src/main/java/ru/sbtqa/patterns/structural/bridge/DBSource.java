package ru.sbtqa.patterns.structural.bridge;

public class DBSource implements DataSource {
    @Override
    public String getParameter(String param) {
        System.out.println("Querying Database for " + param);
        return "DB parameter";
    }
}
