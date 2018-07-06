package ru.sbtqa.patterns.creational;

import org.junit.Test;
import ru.sbtqa.patterns.creational.factory.*;

/**
 * @author Alexey Rumyantsev
 */
public class FactoryTest {

    @Test
    public void nofactoryTest() {
        Car sedan1 = new Sedan(1);
        Car sedan2 = new Sedan(2);
        Car minivan = new Minivan();

        sedan1.accelerate();
        sedan2.accelerate();
        sedan1.brake();
        minivan.accelerate();
        /* .... */
    }

    @Test
    public void factoryTest() {
        CarFactory factory = new CarFactory();

        Car sedan = factory.create(CarType.SEDAN);
        Car minivan = factory.create(CarType.MINIVAN);

        sedan.accelerate();
        sedan.brake();
        //....
    }
}
