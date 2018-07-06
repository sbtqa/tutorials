package ru.sbtqa.patterns.structural;

import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.structural.decorator.Button;
import ru.sbtqa.patterns.structural.decorator.ButtonDecorator;
import ru.sbtqa.patterns.structural.decorator.ConcreteButton;

/**
 * @author Alexey Rumyantsev
 */
public class DecoratorTest {
    @Test
    public void test() {
        Button button = new ConcreteButton();
        button.click();

        System.out.println("=================");
        System.out.println("Adding doubleClick");
        System.out.println("=================");

        //Decorate with doubleClick
        button = ButtonDecorator.decorate(button, ButtonDecorator::doubleClick);
        button.click();

        System.out.println("=================");
        System.out.println("Adding logging");
        System.out.println("=================");

        //Decorate with log
        button = ButtonDecorator.decorate(button, ButtonDecorator::log);
        button.click();
    }
}
