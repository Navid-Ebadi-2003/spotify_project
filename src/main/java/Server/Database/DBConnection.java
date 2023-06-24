package Server.Database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private Connection connection;

    //Constructor

    public DBConnection() {
        //SQLite connection string
        String url;
        if(isWindows()) {
            url = "jdbc:sqlite:src\\main\\java\\DatabaseBackup\\Spotify.db";
        } else {
            url = "jdbc:sqlite:src/main/java/DatabaseBackup/Spotify.db";
        }

        this.connection = null;
        try {
            this.connection = DriverManager.getConnection(url);
            //Write to LOG FILE that connection has been established.
            System.out.println("Connection to database has been established!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean isWindows() {
        String operSys = System.getProperty("os.name").toLowerCase();
        if (operSys.contains("win")) {
            return true;
        } else {
            return false;
        }
    }

    public Connection getConnection() {
        return connection;
    }
}
