package ru.sbtqa.patterns.creational.abstractfactory;

/**
 * @author Alexey Rumyantsev
 */
public interface DeviceFactory {
    Phone createPhone();

    Laptop createLaptop();
}
