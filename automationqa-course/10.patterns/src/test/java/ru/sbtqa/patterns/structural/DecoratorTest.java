package ru.sbtqa.patterns.structural;

import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.structural.decorator.Button;
import ru.sbtqa.patterns.structural.decorator.ButtonDecorator;
import ru.sbtqa.patterns.structural.decorator.ConcreteButton;

import java.util.function.Function;

/**
 * @author Alexey Rumyantsev
 */
class DecoratorTest {
    @Test
    void test() {
        Button button = new ConcreteButton();
        button.click();

        System.out.println("=================");
        System.out.println("Add double click");
        System.out.println("=================");

        Function<Button, Button> function = ButtonDecorator::doubleClick;
        button = function.apply(button);
        button.click();

        System.out.println("=================");
        System.out.println("Add logger");
        System.out.println("=================");

        function = ButtonDecorator::log;
        button = function.apply(button);
        button.click();
    }
}
