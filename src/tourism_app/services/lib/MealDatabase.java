package tourism_app.services.lib;
import tourism_app.tour.meal.Meal;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class MealDatabase {
    private static final String FILE_PATH = "meals.ser";
    private final Map<String, Meal> meals = new LinkedHashMap<>();

    private final Map<Integer, String> fieldOptions = Map.of(
            1, "name",
            2, "type",
            3, "cost",
            4, "mealsPerDay"
    );

    private final Map<String, Predicate<Meal>> searchCriteria;

    public MealDatabase() {
        loadFromFile();
        searchCriteria = initializeSearchCriteria();
    }

    private Map<String, Predicate<Meal>> initializeSearchCriteria() {
        Map<String, Predicate<Meal>> criteria = new HashMap<>();

        criteria.put("name", meal -> {
            System.out.print("Enter meal name to search: ");
            Scanner sc = new Scanner(System.in);
            String name = sc.nextLine().trim();
            return meal.getName().equalsIgnoreCase(name);
        });

        criteria.put("type", meal -> {
            System.out.print("Enter meal type (e.g., vegetarian, vegan): ");
            Scanner sc = new Scanner(System.in);
            String type = sc.nextLine().trim();
            return meal.hasType(type);
        });

        criteria.put("cost", meal -> {
            System.out.print("Enter maximum cost per day: ");
            Scanner sc = new Scanner(System.in);
            try {
                double cost = sc.nextDouble();
                return meal.getCostPerDay() <= cost;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter a numeric value.");
                return false;
            }
        });

        criteria.put("mealsPerDay", meal -> {
            System.out.print("Enter number of meals per day: ");
            Scanner sc = new Scanner(System.in);
            try {
                int mealsPerDay = sc.nextInt();
                return meal.getMealsPerDay() == mealsPerDay;
            } catch (InputMismatchException e) {
                System.out.println("Invalid input. Please enter an integer.");
                return false;
            }
        });

        return criteria;
    }

    public void displaySearchFields() {
        System.out.println("Choose a field to search by:");
        fieldOptions.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public List<Meal> searchMeals(int option) {
        String field = fieldOptions.get(option);
        if (field == null) {
            System.out.println("Invalid option selected.");
            return Collections.emptyList();
        }

        Predicate<Meal> criteria = searchCriteria.get(field);
        return meals.values().stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    public void addMeal(Meal meal) {
        meals.put(meal.getName(), meal);
        saveToFile();
    }

    public Meal getMeal(String name) {
        return meals.get(name);
    }

    public void listAllMeals() {
        if (meals.isEmpty()) {
            System.out.println("No meals available.");
        } else {
            meals.values().forEach(meal -> System.out.println(meal));
        }
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

    public boolean removeMeal(String name) {
        if (meals.remove(name) != null) {
            saveToFile();
            return true;
        }
        return false;
    }

    public Map<String, Meal> getMeals() {
        return meals;
    }

    public int getFieldOptionsSize() {
        return fieldOptions.size();
    }
}
