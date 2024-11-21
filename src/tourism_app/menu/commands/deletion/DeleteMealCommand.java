package tourism_app.menu.commands.deletion;

import tourism_app.services.lib.MealDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.Map;

public class DeleteMealCommand implements Command {
    private final MealDatabase mealDB;
    private final InputValidator inputValidator;

    public DeleteMealCommand(MealDatabase mealDB, InputValidator inputValidator) {
        this.mealDB = mealDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<String, Meal> meals = mealDB.getMeals();

        if (meals.isEmpty()) {
            System.out.println("No meals available to delete.");
            return;
        }

        System.out.println("Available meals to delete:");
        mealDB.listAllMeals();

        System.out.println("Enter the number of the meal to delete:");
        int choice = inputValidator.getValidIntInRange(1, meals.size() + 1);

        String selectedMealName = (String) meals.keySet().toArray()[choice - 1];
        Meal selectedLocation = meals.get(selectedMealName);

        if (mealDB.removeMeal(selectedMealName)) {
            System.out.println("Meal \"" + selectedLocation.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete meal \"" + selectedLocation.getName() + "\".");
        }
    }

    public String getName() {
        return "Delete Meal";
    }
}
