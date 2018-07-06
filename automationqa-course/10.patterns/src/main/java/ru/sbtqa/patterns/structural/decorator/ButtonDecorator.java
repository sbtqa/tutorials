package ru.sbtqa.patterns.structural.decorator;

public class ButtonDecorator {
    public static Button doubleClick(Button button) {
        return () -> {
            button.click();
            button.click();
        };
    }

    public static Button log(Button button) {
        return () -> {
            System.out.println("LOGGER - Clicking the button");
            button.click();
        };
    }
}
