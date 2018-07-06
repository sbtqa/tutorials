package ru.sbtqa.patterns.creational.singleton;

public class DoubleCheckLockSingleton {
    private volatile static DoubleCheckLockSingleton ourInstance;

    public static DoubleCheckLockSingleton getInstance() {
        DoubleCheckLockSingleton localInstance = ourInstance;
        if (localInstance == null) {
            synchronized (DoubleCheckLockSingleton.class) {
                localInstance = ourInstance;
                if (localInstance == null)
                    ourInstance = localInstance = new DoubleCheckLockSingleton();
            }
        }
        return localInstance;
    }

    private DoubleCheckLockSingleton() {
    }
}
