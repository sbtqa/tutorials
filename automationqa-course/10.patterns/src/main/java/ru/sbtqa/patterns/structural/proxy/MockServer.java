package ru.sbtqa.patterns.structural.proxy;

public class MockServer implements Server {
    @Override
    public String sendMessage(String message) {
        System.out.println("SERVER STUB");
        return "Stub reply";
    }
}
