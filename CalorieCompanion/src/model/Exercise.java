package model;

public class Exercise {
    private int id;
    private int userId;
    private String date;
    private String type;
    private int caloriesBurned;

    // Constructor with all fields, including ID
    public Exercise(int id, int userId, String date, String type, int caloriesBurned) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.type = type;
        this.caloriesBurned = caloriesBurned;
    }

    // Constructor without ID, used for new records
    public Exercise(int userId, String date, String type, int caloriesBurned) {
        this.userId = userId;
        this.date = date;
        this.type = type;
        this.caloriesBurned = caloriesBurned;
    }

    // Getters for accessing private fields
    public int getId() {
        return id;
    }

    public int getUserId() {
        return userId;
    }

    public String getDate() {
        return date;
    }

    public String getType() {
        return type;
    }

    public int getCaloriesBurned() {
        return caloriesBurned;
    }
}
