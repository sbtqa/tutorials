package ru.sbtqa.patterns.creational.factory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author Alexey Rumyantsev
 */
public class CarFactory {
    private AtomicInteger counter = new AtomicInteger();

    public Car create(CarType type) {
        switch (type) {
            case SMART:
                return new Smart();
            case MINIVAN:
                return new Minivan();
            case SEDAN:
                return new Sedan(counter.incrementAndGet());
            default:
                throw new IllegalArgumentException("Неизвестный тип авто");
        }
    }
}
