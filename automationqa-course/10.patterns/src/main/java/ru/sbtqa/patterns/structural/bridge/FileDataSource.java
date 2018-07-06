package ru.sbtqa.patterns.structural.bridge;

public class FileDataSource implements DataSource {
    @Override
    public String getParameter(String param) {
        System.out.println("Opening file for " + param);
        return "Parameter from file";
    }
}
