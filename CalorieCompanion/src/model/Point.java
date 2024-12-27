package model;

public class Point {
    private int id;
    private int userId;
    private String date;
    private int points;

    // Constructor with all fields, including ID
    public Point(int id, int userId, String date, int points) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.points = points;
    }

    // Constructor without ID, used for new records
    public Point(int userId, String date, int points) {
        this(0, userId, date, points); // Default ID is 0 for new points
    }

    // Getters and setters for accessing and modifying private fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }
    public String getDate() { return date; }
    public void setDate(String date) { this.date = date; }
    public int getPoints() { return points; }
    public void setPoints(int points) { this.points = points; }
}
