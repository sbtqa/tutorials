package ru.sbtqa.patterns.creational.abstractfactory;

/**
 * @author Alexey Rumyantsev
 */
public class AppleDeviceFactory implements DeviceFactory {
    @Override
    public Phone createPhone() {
        return new IPhone();
    }

    @Override
    public Laptop createLaptop() {
        return new MacBook();
    }
}
