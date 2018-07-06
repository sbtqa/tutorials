package ru.sbtqa.patterns.creational.abstractfactory;

/**
 * @author Alexey Rumyantsev
 */
public class IPhone implements Phone {
    @Override
    public void call(String number) {
        System.out.println("call " + number + " with iPhone");
    }
}
