package ru.sbtqa.patterns.creational;

import org.junit.jupiter.api.Test;
import ru.sbtqa.patterns.creational.abstractfactory.*;

/**
 * @author Alexey Rumyantsev
 */
class AbstractFactoryTest {

    private DeviceFactory factory = new AppleDeviceFactory();

    @Test
    void abstractFactoryTest() {
        Phone phone = factory.createPhone();
        Laptop laptop = factory.createLaptop();

        //some actions
        laptop.turnOn();
        phone.call("+79991234567");
    }
}
