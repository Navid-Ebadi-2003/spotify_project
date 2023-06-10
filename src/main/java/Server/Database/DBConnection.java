package Server.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    //Constructor

    public DBConnection() {
        //SQLite connection string
        String url = "jdbc:sqlite:\\DatabaseBackup\\Spotify.db";
        this.connection = null;
        try {
            this.connection = DriverManager.getConnection(url);
            //Write to LOG FILE that connection has been established.
            System.out.println("Connection to database has been established!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
