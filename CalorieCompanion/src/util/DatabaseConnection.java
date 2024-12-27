package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

public class DatabaseConnection {
    private static final String URL = "jdbc:sqlite:calorie_companion.db";

    // Establishes a connection to the database
    public static Connection connect() {
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(URL);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

    // Initializes the database and creates tables if they do not exist
    public static void initializeDatabase() {
        // SQL statement to create Users table
        String usersTable = "CREATE TABLE IF NOT EXISTS Users ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "name TEXT NOT NULL,"
                + "email TEXT NOT NULL UNIQUE,"
                + "password TEXT NOT NULL,"
                + "weight REAL,"
                + "height REAL,"
                + "calorieGoal INTEGER DEFAULT 0,"
                + "points INTEGER DEFAULT 0"
                + ");";
        
        // SQL statement to create Meals table
        String mealsTable = "CREATE TABLE IF NOT EXISTS Meals ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "userId INTEGER,"
                + "date TEXT,"
                + "type TEXT,"
                + "calories INTEGER,"
                + "FOREIGN KEY (userId) REFERENCES Users(id)"
                + ");";
        
        // SQL statement to create Exercises table
        String exercisesTable = "CREATE TABLE IF NOT EXISTS Exercises ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "userId INTEGER,"
                + "date TEXT,"
                + "type TEXT,"
                + "caloriesBurned INTEGER,"
                + "FOREIGN KEY (userId) REFERENCES Users(id)"
                + ");";
        
        // SQL statement to create Points table
        String pointsTable = "CREATE TABLE IF NOT EXISTS Points ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT,"
                + "userId INTEGER,"
                + "date TEXT,"
                + "points INTEGER,"
                + "FOREIGN KEY (userId) REFERENCES Users(id)"
                + ");";

        // Execute the SQL statements to create tables
        try (Connection conn = connect();
             Statement stmt = conn.createStatement()) {
            stmt.execute(usersTable);
            stmt.execute(mealsTable);
            stmt.execute(exercisesTable);
            stmt.execute(pointsTable);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
