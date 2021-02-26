package me.lucacw.mysqlapi.data;

public class MySQLConnectionData {

    private final String host;
    private final String port;
    private final String username;
    private final String password;
    private final String database;

    public MySQLConnectionData(String host, String port, String username, String password, String database) {
        this.host = host;
        this.port = port;
        this.username = username;
        this.password = password;
        this.database = database;
    }

    public String getHost() {
        return host;
    }

    public String getPort() {
        return port;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getDatabase() {
        return database;
    }
}
