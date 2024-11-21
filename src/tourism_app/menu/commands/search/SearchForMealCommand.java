package tourism_app.menu.commands.search;

import tourism_app.services.lib.MealDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForMealCommand implements Command {
    private final MealDatabase mealDatabase;
    private final InputValidator inputValidator;

    public SearchForMealCommand(MealDatabase mealDatabase, InputValidator inputValidator) {
        this.mealDatabase = mealDatabase;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        mealDatabase.displaySearchFields(); // Вивести доступні поля для пошуку
        int choice = inputValidator.getValidIntInRange(1, mealDatabase.getFieldOptionsSize());

        List<Meal> results = mealDatabase.searchMeals(choice);

        if (results.isEmpty()) {
            System.out.println("No meals found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
        return "Search for meal";
    }
}
