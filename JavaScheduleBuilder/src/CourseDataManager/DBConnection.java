package CourseDataManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
    private static final String DB_URL = "jdbc:sqlite:/Users/harrisonjuneau/Documents/College/Spring 2023/CSC3380 Project/CSC-3380-Spring-23/courses.db";

    public static Connection getConnection() {
        Connection connection = null;
        try {
            Class.forName("org.sqlite.JDBC");
            connection = DriverManager.getConnection(DB_URL);
        } catch (ClassNotFoundException | SQLException e) {
            System.err.println("Error connecting to the database: " + e.getMessage());
        }
        return connection;
    }
}

