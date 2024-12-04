package tourism_app.control.commands.search;

import tourism_app.services.lib.MealDatabase;
import tourism_app.services.searchService.MealSearchService;
import tourism_app.control.commands.Command;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForMealCommand implements Command {
    private final MealDatabase mealDatabase;
    private final MealSearchService searchService;

    public SearchForMealCommand(MealDatabase mealDatabase, InputValidator inputValidator) {
        this.mealDatabase = mealDatabase;
        this.searchService = new MealSearchService(inputValidator);
    }

    @Override
    public void execute() {
        List<Meal> meals = mealDatabase.getAllItemsAsList();
        List<Meal> results = searchService.search(meals);

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
