package ru.sbtqa.patterns.creational.abstractfactory;

/**
 * @author Alexey Rumyantsev
 */
public class MicrosoftDeviceFactory implements DeviceFactory {
    @Override
    public Phone createPhone() {
        return new Lumia();
    }

    @Override
    public Laptop createLaptop() {
        return new Surface();
    }
}
