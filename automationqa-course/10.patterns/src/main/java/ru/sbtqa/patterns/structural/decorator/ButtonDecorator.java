package ru.sbtqa.patterns.structural.decorator;

import java.util.function.Function;
import java.util.stream.Stream;

/**
 * @author Alexey Rumyantsev
 */
public interface ButtonDecorator extends Button {
    static Button decorate(Button button, Function<Button, Button>... decorators) {
        return Stream.of(decorators)
                .reduce(Function.identity(), Function::andThen)
                .apply(button);
    }

    static Button log(Button button) {
        return () -> {
            System.out.println("LOG :: Clicking the Button");
            button.click();
        };
    }

    static Button doubleClick(Button button) {
        return () -> {
            button.click();
            button.click();
        };
    }
}
