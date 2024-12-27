package util;

import DAO.*;
import model.*;
import java.text.SimpleDateFormat;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        UserDAO userDAO = new UserDAO();
        MealDAO mealDAO = new MealDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();
        PointDAO pointDAO = new PointDAO(); 
        Scanner scanner = new Scanner(System.in);

        System.out.println("1. Create an Account");
        System.out.println("2. Log in to Existing Account");
        int option = scanner.nextInt();
        scanner.nextLine();  

        User user = null;

        if (option == 1) {
            // Create an account
            System.out.println("Enter name:");
            String name = scanner.nextLine();
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            System.out.println("Enter weight:");
            double weight = scanner.nextDouble();
            System.out.println("Enter height:");
            double height = scanner.nextDouble();
            System.out.println("Enter daily calorie goal:");
            int calorieGoal = scanner.nextInt();
            scanner.nextLine();  
            user = new User(name, email, password, weight, height, calorieGoal, 0);
            userDAO.addUser(user);
            System.out.println("Account created successfully. Please log in.");
        } else if (option == 2) {
            // Log in to an existing account
            System.out.println("Enter email:");
            String email = scanner.nextLine();
            System.out.println("Enter password:");
            String password = scanner.nextLine();
            user = userDAO.getUserByEmailAndPassword(email, password);
            if (user == null) {
                System.out.println("Invalid email or password. Please try again.");
                System.exit(1);
            }
        }

        System.out.println("Login successful. Welcome, " + user.getName() + "!");
        boolean running = true;
        String currentDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());

        while (running) {
            System.out.println("1. Add Meal");
            System.out.println("2. Add Exercise");
            System.out.println("3. View Points");
            System.out.println("4. Select Group");
            System.out.println("5. Check Daily Calorie Intake");
            System.out.println("6. Exit");
            int choice = scanner.nextInt();
            scanner.nextLine();  

            switch (choice) {
                case 1:
                    // Add Meal
                    System.out.println("Enter meal type:");
                    String mealType = scanner.nextLine();
                    System.out.println("Enter meal calories:");
                    int mealCalories = scanner.nextInt();
                    scanner.nextLine(); 

                    Meal meal = new Meal(user.getId(), currentDate, mealType, mealCalories);
                    mealDAO.addMeal(meal);
                    System.out.println("Meal added successfully.");

                    // Update points
                    int dailyCaloriesAfterMeal = calculateDailyCalorieIntake(user.getId(), currentDate);
                    updatePoints(userDAO, pointDAO, user, dailyCaloriesAfterMeal);
                    break;

                case 2:
                    // Add Exercise
                    System.out.println("Enter exercise type:");
                    String exerciseType = scanner.nextLine();
                    System.out.println("Enter exercise calories burned:");
                    int exerciseCalories = scanner.nextInt();
                    scanner.nextLine();  

                    Exercise exercise = new Exercise(user.getId(), currentDate, exerciseType, exerciseCalories);
                    exerciseDAO.addExercise(exercise);
                    System.out.println("Exercise added successfully.");

                    // Update points
                    int dailyCaloriesAfterExercise = calculateDailyCalorieIntake(user.getId(), currentDate);
                    updatePoints(userDAO, pointDAO, user, dailyCaloriesAfterExercise);
                    break;

                case 3:
                    // View Points
                    int points = userDAO.getUserPoints(user.getId());
                    System.out.println("Total points: " + points);
                    break;

                case 4:
                    // Select Group
                    System.out.println("Enter new group (bulking/cutting/maintaining):");
                    String newGroup = scanner.nextLine();
                    userDAO.updateUserCalorieGoal(user.getId(), user.getCalorieGoal());
                    System.out.println("Group updated to " + newGroup);
                    break;

                case 5:
                    // Check Daily Calorie Intake
                    int dailyCalories = calculateDailyCalorieIntake(user.getId(), currentDate);
                    System.out.println("Net calorie intake for today: " + dailyCalories);
                    break;

                case 6:
                    // Exit
                    running = false;
                    System.out.println("Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please try again.");
                    break;
            }
        }

        scanner.close();
    }

    // Calculate total calorie intake minus calories burned for the day
    private static int calculateDailyCalorieIntake(int userId, String date) {
        MealDAO mealDAO = new MealDAO();
        ExerciseDAO exerciseDAO = new ExerciseDAO();

        List<Meal> meals = mealDAO.getMealsByUserIdAndDate(userId, date);
        List<Exercise> exercises = exerciseDAO.getExercisesByUserIdAndDate(userId, date);

        int totalCalories = meals.stream().mapToInt(Meal::getCalories).sum();
        int totalCaloriesBurned = exercises.stream().mapToInt(Exercise::getCaloriesBurned).sum();

        return totalCalories - totalCaloriesBurned;
    }

    // Update user points based on daily calorie intake
    private static void updatePoints(UserDAO userDAO, PointDAO pointDAO, User user, int dailyCalories) {
        int currentPoints = userDAO.getUserPoints(user.getId());
        int calorieGoal = user.getCalorieGoal();

        if (dailyCalories >= calorieGoal) {
            currentPoints += 100;
        } else {
            currentPoints -= 50;
        }

        userDAO.updateUserPoints(user.getId(), currentPoints);

        Point point = new Point(user.getId(), new SimpleDateFormat("yyyy-MM-dd").format(new Date()), currentPoints);
        pointDAO.updatePoint(point); 
    }
}
