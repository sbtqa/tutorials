package ru.sbtqa.patterns.creational;

import org.testng.annotations.Test;
import ru.sbtqa.patterns.creational.abstractfactory.*;

/**
 * @author Alexey Rumyantsev
 */
public class AbstractFactoryTest {

    @Test
    public void abstractFactoryTest() {
        DeviceFactory factory = new MicrosoftDeviceFactory();
        Phone phone = factory.createPhone();
        Laptop laptop = factory.createLaptop();

        //some actions
        laptop.turnOn();
        phone.call("+79991234567");
    }
}
