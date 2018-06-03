package ru.sbtqa.patterns.behavioural.chain;

public class Request {

    private RequestType type;

    private boolean isHandled = false;

    public Request(RequestType type) {
        this.type = type;
    }

    public RequestType getType() {
        return type;
    }

    public boolean isHandled() {
        return isHandled;
    }

    public void setHandled(boolean handled) {
        isHandled = handled;
    }
}
