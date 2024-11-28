package tourism_app.services.lib;

import tourism_app.tour.location.Location;
import tourism_app.tour.meal.Meal;

import java.io.*;
import java.util.*;

public class MealDatabase {
    private static final String FILE_PATH = "meals.ser";
    private final Map<String, Meal> meals = new LinkedHashMap<>();

    public MealDatabase() {
        loadFromFile();
    }

    public void addMeal(Meal meal) {
        meals.put(meal.getName(), meal);
        saveToFile();
    }

    public Map<String, Meal> getMeals() {
        return meals;
    }

    public List<Meal> getMealsAsList() {
        return new ArrayList<>(meals.values());
    }

    public void listAllMeals() {
        if (meals.isEmpty()) {
            System.out.println("No meals available.");
            return;
        }

        int index = 1;
        for (Meal meal : meals.values()) {
            System.out.println(index + ". " + meal.toString());
            index++;
        }
    }

    public boolean removeMeal(String name) {
        if (meals.remove(name) != null) {
            saveToFile();
            return true;
        }
        return false;
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH,false))) {
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
