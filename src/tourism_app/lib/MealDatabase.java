package tourism_app.lib;

import tourism_app.tour.meal.Meal;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class MealDatabase implements TourismDatabase {
    private static final String FILE_PATH = "meals.ser";
    private final Map<String, Meal> meals = new HashMap<>();

    public MealDatabase() {
        loadFromFile();
    }

    public void addMeal(Meal meal) {
        meals.put(meal.getName(), meal);
        saveToFile();
    }

    public Meal getMeal(String name) {
        return meals.get(name);
    }

    public void listAllMeals() {
        meals.values().forEach(meal -> System.out.println("Meal: " + meal.getName() + ", Cost per day: " + meal.getCostPerDay()));
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(meals);
        } catch (IOException e) {
            System.out.println("Error saving meals: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            meals.putAll((Map<String, Meal>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading meals: " + e.getMessage());
        }
    }
}