package model;

public class Friend {
    private int id;
    private int userId;
    private int friendId;

    // Constructor with all fields, including ID
    public Friend(int id, int userId, int friendId) {
        this.id = id;
        this.userId = userId;
        this.friendId = friendId;
    }

    // Constructor without ID, used for new records
    public Friend(int userId, int friendId) {
        this(0, userId, friendId);  // Default ID is 0 for new friends
    }

    // Getters and setters for accessing and modifying private fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public int getFriendId() { return friendId; }
    public void setFriendId(int friendId) { this.friendId = friendId; }
}
