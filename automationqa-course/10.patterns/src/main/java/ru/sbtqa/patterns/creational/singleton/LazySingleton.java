package ru.sbtqa.patterns.creational.singleton;

public class LazySingleton {
    private static volatile LazySingleton ourInstance;

    public static LazySingleton getInstance() {
        LazySingleton localInstance = ourInstance;
        if (localInstance == null) {
            synchronized (LazySingleton.class) {
                localInstance = ourInstance;
                if (localInstance == null)
                    ourInstance = localInstance = new LazySingleton();
            }
        }
        return localInstance;
    }

    private LazySingleton() {
    }
}
