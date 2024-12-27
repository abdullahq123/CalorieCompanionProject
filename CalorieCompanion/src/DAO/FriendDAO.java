package DAO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Friend;
import util.DatabaseConnection;

public class FriendDAO {
    // Connects to the database
    private Connection connect() {
        return DatabaseConnection.connect();
    }

    // Adds a new friend record
    public void addFriend(Friend friend) {
        String sql = "INSERT INTO Friends(userId, friendId) VALUES(?, ?)";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute insert
            pstmt.setInt(1, friend.getUserId());
            pstmt.setInt(2, friend.getFriendId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Retrieves all friend records
    public List<Friend> getAllFriends() {
        String sql = "SELECT * FROM Friends";
        List<Friend> friends = new ArrayList<>();
        try (Connection conn = connect();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {
            // Add each record to the friends list
            while (rs.next()) {
                Friend friend = new Friend(rs.getInt("id"), rs.getInt("userId"), rs.getInt("friendId"));
                friends.add(friend);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return friends;
    }

    // Updates an existing friend record
    public void updateFriend(Friend friend) {
        String sql = "UPDATE Friends SET userId = ?, friendId = ? WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            // Set values and execute update
            pstmt.setInt(1, friend.getUserId());
            pstmt.setInt(2, friend.getFriendId());
            pstmt.setInt(3, friend.getId());
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    // Deletes a friend record by ID
    public void deleteFriend(int friendId) {
        String sql = "DELETE FROM Friends WHERE id = ?";
        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, friendId);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}
