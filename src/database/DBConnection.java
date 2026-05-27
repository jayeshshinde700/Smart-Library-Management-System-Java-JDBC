package database;

import java.sql.Connection;
import java.sql.DriverManager;

public class DBConnection {

    private static DBConnection instance;
    private Connection connection;

    private String url =
            "jdbc:mysql://localhost:3307/smart_library";

    private String username = "root";
    private String password = "root";

    private DBConnection() {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");

            connection = DriverManager.getConnection(
                    url,
                    username,
                    password
            );

            System.out.println("Database Connected");

        } catch(Exception e) {
            e.printStackTrace();
        }
    }

    public static DBConnection getInstance() {

        if(instance == null) {
            instance = new DBConnection();
        }

        return instance;
    }

    public Connection getConnection() {
        return connection;
    }
}