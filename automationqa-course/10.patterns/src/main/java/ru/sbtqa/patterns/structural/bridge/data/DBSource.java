package ru.sbtqa.patterns.structural.bridge.data;

import java.sql.*;
import java.util.NoSuchElementException;

public class DBSource implements DataSource {

    private final String url;
    private final String user;
    private final String password;

    public DBSource(String url, String user, String password) {
        this.url = url;
        this.user = user;
        this.password = password;
    }

    @Override
    public String getParameter(String param) {
        System.out.println("Querying Database for " + param);
        try (Connection connection = DriverManager.getConnection(url, user, password);
             PreparedStatement statement = connection.prepareStatement("SELECT value FROM params WHERE param_name=?")) {
            statement.setString(1, param);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getString(1);
            throw new NoSuchElementException(param + "not found!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
