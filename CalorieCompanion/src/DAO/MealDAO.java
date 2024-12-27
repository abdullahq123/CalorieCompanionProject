package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Meal;
import util.DatabaseConnection;

public class MealDAO {
    // Connects to the database
    private Connection connect() {
        return DatabaseConnection.connect();
    }

    // Adds a new meal record
    public void addMeal(Meal meal) {
        String sql = "INSERT INTO Meals(userId, date, mealType, calories) VALUES(?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute insert
            pstmt.setInt(1, meal.getUserId());
            pstmt.setString(2, meal.getDate());
            pstmt.setString(3, meal.getMealType());
            pstmt.setInt(4, meal.getCalories());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieves meals by user ID and date
    public List<Meal> getMealsByUserIdAndDate(int userId, String date) {
        String sql = "SELECT * FROM Meals WHERE userId = ? AND date = ?";
        List<Meal> meals = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters and execute query
            pstmt.setInt(1, userId);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            // Add each record to the meals list
            while (rs.next()) {
                Meal meal = new Meal(rs.getInt("id"), rs.getInt("userId"), rs.getString("date"),
                                     rs.getString("mealType"), rs.getInt("calories"));
                meals.add(meal);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return meals;
    }
}
