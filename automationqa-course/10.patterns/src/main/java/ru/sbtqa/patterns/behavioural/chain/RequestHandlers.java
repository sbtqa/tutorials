package ru.sbtqa.patterns.behavioural.chain;

public interface RequestHandlers {
    static Request handleImage(Request request) {
        System.out.println("Enter handleImage");
        if (!request.isHandled() && request.getType().equals(RequestType.IMAGE)) {
            request.setHandled(true);
            System.out.println("Handled by Image Handler");
        }
        return request;
    }

    static Request handleExecutable(Request request) {
        System.out.println("Enter handleExecutable");
        if (!request.isHandled() && request.getType().equals(RequestType.EXECUTABLE)) {
            request.setHandled(true);
            System.out.println("Handled by Executable Handler");
        }
        return request;
    }

    static Request handleAudio(Request request) {
        System.out.println("Enter handleAudio");
        if (!request.isHandled() && request.getType().equals(RequestType.AUDIO)) {
            request.setHandled(true);
            System.out.println("Handled by Audio Handler");
        }
        return request;
    }
}
