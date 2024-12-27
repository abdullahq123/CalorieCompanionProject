package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import model.Point;
import util.DatabaseConnection;

public class PointDAO {

    // Connects to the database
    private Connection connect() {
        return DatabaseConnection.connect();
    }

    // Adds or updates a point record
    public void addOrUpdatePoint(Point point) {
        String sql = "INSERT INTO Points (userId, date, points) VALUES (?, ?, ?) "
                   + "ON CONFLICT(userId, date) DO UPDATE SET points = excluded.points";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute insert or update
            pstmt.setInt(1, point.getUserId());
            pstmt.setString(2, point.getDate());
            pstmt.setInt(3, point.getPoints());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieves all point records
    public List<Point> getAllPoints() {
        String sql = "SELECT * FROM Points";
        List<Point> points = new ArrayList<>();
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {
            // Add each record to the points list
            while (rs.next()) {
                Point point = new Point(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getString("date"),
                    rs.getInt("points")
                );
                points.add(point);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return points;
    }

    // Retrieves points for a specific user on a specific date
    public Point getPointsForDate(int userId, String date) {
        String sql = "SELECT * FROM Points WHERE userId = ? AND date = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set parameters and execute query
            pstmt.setInt(1, userId);
            pstmt.setString(2, date);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Point(
                    rs.getInt("id"),
                    rs.getInt("userId"),
                    rs.getString("date"),
                    rs.getInt("points")
                );
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return null;
    }

    // Deletes a point record by ID
    public void deletePoint(int pointId) {
        String sql = "DELETE FROM Points WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, pointId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
