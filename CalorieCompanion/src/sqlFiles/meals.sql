CREATE TABLE IF NOT EXISTS Meals (
    id INTEGER PRIMARY KEY AUTOINCREMENT,
    userId INTEGER,
    date TEXT NOT NULL,
    mealType TEXT NOT NULL,
    calories INTEGER NOT NULL,
    FOREIGN KEY (userId) REFERENCES Users(id)
);
