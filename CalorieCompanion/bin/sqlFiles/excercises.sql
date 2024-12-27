CREATE TABLE Exercises (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER,
    date TEXT,
    exerciseType TEXT,
    caloriesBurned INTEGER,
    FOREIGN KEY (userId) REFERENCES Users(id)
);
