package ru.sbtqa.patterns.structural.proxy;

public class ServerStub implements Server {
    @Override
    public String sendMessage(String message) {
        return "{\"success\":\"true\"}";
    }
}
