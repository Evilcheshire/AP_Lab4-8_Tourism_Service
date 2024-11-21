package tourism_app.menu.commands.search;

import tourism_app.services.lib.MealDatabase;
import tourism_app.services.searchService.MealSearchService;
import tourism_app.menu.commands.Command;
import tourism_app.tour.meal.Meal;
import tourism_app.services.utils.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class SearchForMealCommand implements Command {
    private final MealDatabase mealDatabase;
    private final MealSearchService searchService;
    private final InputValidator inputValidator;

    public SearchForMealCommand(MealDatabase mealDatabase, InputValidator inputValidator) {
        this.mealDatabase = mealDatabase;
        this.searchService = new MealSearchService(inputValidator);
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        searchService.displayAvailableSearchFields();

        int field = inputValidator.getValidIntInRange(1, searchService.getSearchFields().size());

        List<Meal> results = searchService.search(searchService.getSearchFields().get(field - 1),
                new ArrayList<>(mealDatabase.getMeals().values()));

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
