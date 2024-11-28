package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.Map;
import java.util.stream.Collectors;

public class DeleteMealCommand implements Command {
    private final DatabaseManager databaseManager;
    private final InputValidator inputValidator;

    public DeleteMealCommand(DatabaseManager databaseManager, InputValidator inputValidator) {
        this.databaseManager = databaseManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<String, Meal> meals = databaseManager.getMealDatabase().getMeals();

        if (meals.isEmpty()) {
            System.out.println("No meals available to delete.");
            return;
        }

        System.out.println("Available meals to delete:");
        databaseManager.getMealDatabase().listAllMeals();

        int choice = inputValidator.getValidIntInRange("Enter the number of the meal to delete:",1, meals.size());

        String selectedMealName = (String) meals.keySet().toArray()[choice - 1];
        Meal selectedMeal = meals.get(selectedMealName);

        if (databaseManager.getMealDatabase().removeMeal(selectedMealName)) {
            System.out.println("Meal \"" + selectedMeal.getName() + "\" has been deleted.");

            databaseManager.getTourDatabase().getTours().stream()
                    .filter(tour -> selectedMeal.equals(tour.getMeal()))
                    .collect(Collectors.toList())
                    .forEach(tour -> {
                        databaseManager.getTourDatabase().removeTour(tour.getName(), () ->
                                System.out.println("Tour \"" + tour.getName() + "\" removed due to missing meal."));
                        databaseManager.getUserTourDatabase().removeTourFromUsers(tour.getName());
                    });

            databaseManager.saveAllDatabases();
        } else {
            System.out.println("Failed to delete meal \"" + selectedMeal.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a meal";
    }
}
