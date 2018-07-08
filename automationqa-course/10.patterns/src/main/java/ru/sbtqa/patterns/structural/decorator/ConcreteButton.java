package ru.sbtqa.patterns.structural.decorator;

/**
 * @author Alexey Rumyantsev
 */
public class ConcreteButton implements Button {

    @Override
    public void click() {
        System.out.println("Clicked");
    }
}
