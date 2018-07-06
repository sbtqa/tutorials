package ru.sbtqa.patterns.creational.singleton;

public class LazySingleton {
    private static LazySingleton ourInstance;

    public synchronized static LazySingleton getInstance() {
        if (ourInstance == null)
            ourInstance = new LazySingleton();
        return ourInstance;
    }

    private LazySingleton() {
    }
}
