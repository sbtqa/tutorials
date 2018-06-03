package ru.sbtqa.patterns.creational.singleton;

import java.nio.channels.ConnectionPendingException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public enum EnumSingleton {
    INSTANCE;

    private Connection connection;

    EnumSingleton() {
        try {
            connection = DriverManager.getConnection("localhost:port");
        } catch (SQLException e) {
            e.printStackTrace();
            throw new ConnectionPendingException();
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
