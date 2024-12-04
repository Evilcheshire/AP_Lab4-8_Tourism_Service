package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class DeleteMealCommand implements Command {
    private final DatabaseManager databaseManager;
    private final InputValidator inputValidator;

    public DeleteMealCommand(DatabaseManager databaseManager, InputValidator inputValidator) {
        this.databaseManager = databaseManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        List<Meal> meals = databaseManager.getMealDatabase().getAllItemsAsList();

        if (meals.isEmpty()) {
            System.out.println("No meals available to delete.");
            return;
        }

        System.out.println("Available meals to delete:");
        databaseManager.getMealDatabase().listAllItems();

        int choice = inputValidator.getValidIntInRange("Enter the number of the meal to delete:", 1, meals.size());
        Meal selectedMeal = meals.get(choice - 1);

        boolean isRemoved = databaseManager.getMealDatabase().removeItem(selectedMeal.getName(), () -> {
            databaseManager.getTourDatabase().getAllItemsAsList().stream()
                    .filter(tour -> selectedMeal.equals(tour.getMeal()))
                    .forEach(tour -> {
                        databaseManager.getTourDatabase().removeItem(tour.getName(), () ->
                                System.out.println("Tour \"" + tour.getName() + "\" removed due to missing meal."));
                        databaseManager.getUserTourDatabase().removeTourFromUsers(tour.getName());
                    });
        });

        if (isRemoved) {
            System.out.println("Meal \"" + selectedMeal.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete meal \"" + selectedMeal.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a meal";
    }
}
