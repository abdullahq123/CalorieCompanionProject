package model;

public class User {
    private int id;
    private String name;
    private String email;
    private String password;
    private double weight;
    private double height;
    private int calorieGoal;
    private int points;

    // Constructor with all fields, including ID
    public User(int id, String name, String email, String password, double weight, double height, int calorieGoal, int points) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.password = password;
        this.weight = weight;
        this.height = height;
        this.calorieGoal = calorieGoal;
        this.points = points;
    }

    // Constructor without ID, used for new records
    public User(String name, String email, String password, double weight, double height, int calorieGoal, int points) {
        this(0, name, email, password, weight, height, calorieGoal, points); // Default ID is 0
    }

    // Getters for accessing private fields
    public int getId() { return id; }
    public String getName() { return name; }
    public String getEmail() { return email; }
    public String getPassword() { return password; }
    public double getWeight() { return weight; }
    public double getHeight() { return height; }
    public int getCalorieGoal() { return calorieGoal; }
    public int getPoints() { return points; }
}
