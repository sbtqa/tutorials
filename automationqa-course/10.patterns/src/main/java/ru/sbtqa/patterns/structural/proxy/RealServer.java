package ru.sbtqa.patterns.structural.proxy;

public class RealServer implements Server {
    @Override
    public String sendMessage(String message) {
        /**
         * TODO some complicated logic ¯\_(ツ)_/¯
         * */
        return "Real sever reply";
    }
}
