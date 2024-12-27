package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Exercise;
import util.DatabaseConnection;

public class ExerciseDAO {
    // Connects to the database
    private Connection connect() {
        return DatabaseConnection.connect();
    }

    // Adds a new exercise record
    public void addExercise(Exercise exercise) {
        String sql = "INSERT INTO Exercises(userId, date, exerciseType, caloriesBurned) VALUES(?, ?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute insert
            pstmt.setInt(1, exercise.getUserId());
            pstmt.setString(2, exercise.getDate());
            pstmt.setString(3, exercise.getExerciseType());
            pstmt.setInt(4, exercise.getCaloriesBurned());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieves exercises by user ID and date
    public List<Exercise> getExercisesByUserIdAndDate(int userId, String date) {
        String sql = "SELECT * FROM Exercises WHERE userId = ? AND date = ?";
        List<Exercise> exercises = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters and execute query
            pstmt.setInt(1, userId);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                Exercise exercise = new Exercise(rs.getInt("id"), rs.getInt("userId"), rs.getString("date"),
                                                 rs.getString("exerciseType"), rs.getInt("caloriesBurned"));
                exercises.add(exercise);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exercises;
    }
}
