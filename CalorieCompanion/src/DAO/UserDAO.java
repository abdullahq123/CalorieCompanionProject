package DAO;

import java.sql.*;
import model.User;
import util.DatabaseConnection;

public class UserDAO {
    
    // Connects to the database
    private Connection connect() {
        return DatabaseConnection.connect();
    }

    // Adds a new user record
    public void addUser(User user) {
        String sql = "INSERT INTO Users(name, email, password, weight, height, calorieGoal, points) VALUES(?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute insert
            pstmt.setString(1, user.getName());
            pstmt.setString(2, user.getEmail());
            pstmt.setString(3, user.getPassword());
            pstmt.setDouble(4, user.getWeight());
            pstmt.setDouble(5, user.getHeight());
            pstmt.setInt(6, user.getCalorieGoal());
            pstmt.setInt(7, user.getPoints());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieves a user by email and password
    public User getUserByEmailAndPassword(String email, String password) {
        String sql = "SELECT * FROM Users WHERE email = ? AND password = ?";
        User user = null;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters and execute query
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            // If a matching user is found, create a User object
            if (rs.next()) {
                user = new User(rs.getInt("id"), rs.getString("name"), rs.getString("email"),
                                rs.getString("password"), rs.getDouble("weight"), rs.getDouble("height"),
                                rs.getInt("calorieGoal"), rs.getInt("points"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return user;
    }

    // Retrieves the points of a user by their ID
    public int getUserPoints(int userId) {
        String sql = "SELECT points FROM Users WHERE id = ?";
        int points = 0;
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the user ID and execute query
            pstmt.setInt(1, userId);
            ResultSet rs = pstmt.executeQuery();
            // If the user is found, get their points
            if (rs.next()) {
                points = rs.getInt("points");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return points;
    }

    // Updates the points of a user by their ID
    public void updateUserPoints(int userId, int points) {
        String sql = "UPDATE Users SET points = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the new points and execute update
            pstmt.setInt(1, points);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Updates the calorie goal of a user by their ID
    public void updateUserCalorieGoal(int userId, int calorieGoal) {
        String sql = "UPDATE Users SET calorieGoal = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set the new calorie goal and execute update
            pstmt.setInt(1, calorieGoal);
            pstmt.setInt(2, userId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
