package ru.sbtqa.patterns.behavioural.chain;

public interface RequestHandlers {
    static Request handleJSON(Request request) {
        System.out.println("Enter handleJSON");
        if (!request.isHandled() && request.getType() == RequestType.JSON) {
            request.setHandled(true);
            System.out.println("Handled by JSON Handler");
        }
        return request;
    }

    static Request handleXML(Request request) {
        System.out.println("Enter handleXML");
        if (!request.isHandled() && request.getType() == RequestType.XML) {
            request.setHandled(true);
            System.out.println("Handled by XML Handler");
        }
        return request;
    }

    static Request handleBinary(Request request) {
        System.out.println("Enter handleBinary");
        if (!request.isHandled() && request.getType() == RequestType.BINARY) {
            request.setHandled(true);
            System.out.println("Handled by BINARY Handler");
        }
        return request;
    }
}
