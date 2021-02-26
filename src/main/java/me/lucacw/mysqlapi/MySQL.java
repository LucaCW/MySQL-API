package me.lucacw.mysqlapi;

import me.lucacw.mysqlapi.data.MySQLConnectionData;

import java.sql.*;
import java.util.function.Supplier;

public class MySQL {

    private static final Supplier<String> PREFIX = () -> "[MySQL] -> ";

    public String host;
    public String database;
    public String user;
    public String password;
    public String port;

    public Connection connection;
    public boolean connected;

    public MySQL(MySQLConnectionData mySQLConnectionData) {
        this(mySQLConnectionData.getHost(), mySQLConnectionData.getDatabase(), mySQLConnectionData.getUsername(), mySQLConnectionData.getPassword(), mySQLConnectionData.getPort());
    }

    public MySQL(String host, String database, String user, String password, String port) {
        this.host = host;
        this.database = database;
        this.user = user;
        this.password = password;
        this.port = port;
    }

    public void connect() {
        try {
            connection = DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + database + "?autoReconnect=true", user, password);
            System.out.println(PREFIX.get() + "Successfully connected to MySQL-Database");
            this.connected = true;
        } catch (SQLException exception) {
            System.out.println(PREFIX.get() + "Could not connect to MySQL-Database. ERROR: " + exception.getMessage());
            this.connected = false;
        }
    }

    public void close() {
        try {
            if (this.connection != null) {
                connection.close();
                System.out.println(PREFIX.get() + "Succesfully closed connection to MySQL-Database.");
                connected = false;
            } else {
                System.out.println(PREFIX.get() + "MySQL is not connected.");
            }
        } catch (SQLException exception) {
            System.out.println(PREFIX.get() + "Could not close MySQL-Connection. ERROR: " + exception.getMessage());
        }
    }

    public void update(String query) {
        try {
            Statement statement = connection.createStatement();
            statement.executeUpdate(query);
            statement.close();
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }

    public ResultSet getResult(String query) {
        try {
            Statement statement = connection.createStatement();
            return statement.executeQuery(query);
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
        return null;
    }


    public boolean isConnected() {
        return connected;
    }
}
