package model;

public class Meal {
    private int id;
    private int userId;
    private String date;
    private String type;
    private int calories;

    // Constructor with all fields, including ID
    public Meal(int id, int userId, String date, String type, int calories) {
        this.id = id;
        this.userId = userId;
        this.date = date;
        this.type = type;
        this.calories = calories;
    }

    // Constructor without ID, used for new records
    public Meal(int userId, String date, String type, int calories) {
        this(0, userId, date, type, calories);  // Default ID is 0 for new meals
    }

    // Getters for accessing private fields
    public int getId() { return id; }
    public int getUserId() { return userId; }
    public String getDate() { return date; }
    public String getType() { return type; }
    public int getCalories() { return calories; }
}
