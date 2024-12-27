package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Group;
import util.DatabaseConnection;

public class GroupDAO {
    // Connects to the database
    private Connection connect() {
        return DatabaseConnection.connect();
    }

    // Adds a new group record
    public void addGroup(Group group) {
        String sql = "INSERT INTO Groups(name, description, type) VALUES(?, ?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute insert
            pstmt.setString(1, group.getName());
            pstmt.setString(2, group.getDescription());
            pstmt.setString(3, group.getType());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieves all group records
    public List<Group> getAllGroups() {
        String sql = "SELECT * FROM Groups";
        List<Group> groups = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Add each record to the groups list
            while (rs.next()) {
                Group group = new Group(rs.getInt("id"), rs.getString("name"), rs.getString("description"),
                                        rs.getString("type"));
                groups.add(group);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return groups;
    }

    // Updates an existing group record
    public void updateGroup(Group group) {
        String sql = "UPDATE Groups SET name = ?, description = ?, type = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute update
            pstmt.setString(1, group.getName());
            pstmt.setString(2, group.getDescription());
            pstmt.setString(3, group.getType());
            pstmt.setInt(4, group.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletes a group record by ID
    public void deleteGroup(int groupId) {
        String sql = "DELETE FROM Groups WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, groupId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
