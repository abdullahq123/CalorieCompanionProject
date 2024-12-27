package model;

public class Group {
    private int id;
    private String name;
    private String description;
    private String type;

    // Constructor with all fields, including ID
    public Group(int id, String name, String description, String type) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.type = type;
    }

    // Constructor without ID, used for new records
    public Group(String name, String description, String type) {
        this(0, name, description, type);  // Default ID is 0 for new groups
    }

    // Getters and setters for accessing and modifying private fields
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }
    public String getType() { return type; }
    public void setType(String type) { this.type = type; }
}
