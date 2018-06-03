package ru.sbtqa.patterns.creational;

import org.junit.Test;
import ru.sbtqa.patterns.creational.factory.*;

/**
 * @author Alexey Rumyantsev
 */
public class FactoryTest {

    @Test
    public void nofactoryTest() {
        Car sedan1 = new Sedan();
        Car sedan2 = new Sedan();
        Car minivan = new Minivan();

        sedan1.accelerate();
        sedan2.accelerate();
        sedan1.brake();
        //....
    }

    @Test
    public void factoryTest() {
        CarFactory factory = new CarFactory();
        Car car = factory.create(CarType.SMART);

        car.accelerate();
        car.brake();
        //....
    }
}
