package tourism_app.control.commands.creation;

import tourism_app.services.lib.MealDatabase;
import tourism_app.control.commands.Command;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.ArrayList;
import java.util.List;
public class CreateMealCommand implements Command {
    private final MealDatabase mealDB;
    private final InputValidator inputValidator;

    public CreateMealCommand(MealDatabase mealDB, InputValidator inputValidator) {
        this.mealDB = mealDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        System.out.println("Creating a new meal:");

        String name = inputValidator.getValidString("Enter the meal name: ");

        if (mealDB.getMeals().containsKey(name)) {
            System.out.println("A meal with this name already exists.");
            return;
        }

        List<String> types = new ArrayList<>();
        while (true) {
            String type = inputValidator.getValidString("Enter a type of meal (e.g., Vegetarian, Vegan, Gluten-Free): ");
            types.add(type);

            System.out.print("Would you like to add another type? (y/n): ");
            boolean choice = inputValidator.getYesOrNo();
            if (!choice) {
                break;
            }
        }

        double costPerDay = inputValidator.getValidPositiveDouble("Enter the cost per day: ");

        int mealsPerDay = inputValidator.getValidIntInRange("Enter the number of meals per day (max - 6): ",1, 6);

        Meal newMeal = new Meal(name, types, costPerDay, mealsPerDay);
        mealDB.addMeal(newMeal);

        System.out.println("Meal \"" + name + "\" has been successfully created!");
        System.out.println(newMeal);
    }

    @Override
    public String getName() {
        return "Create a new meal";
    }
}