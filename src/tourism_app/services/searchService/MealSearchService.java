package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.meal.Meal;

import java.util.function.Predicate;

public class MealSearchService extends SearchService<Meal> {
    public MealSearchService(InputValidator inputValidator) {
        super(inputValidator);
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("Name", this::searchByName);
        addSearchCriterion("Type", this::searchByType);
        addSearchCriterion("Meals per Day", this::searchByMealsPerDay);
        addSearchCriterion("Cost per Day", this::searchByCostPerDay);
    }

    private Predicate<Meal> searchByName() {
        String name = inputValidator.getValidString("Enter meal name: ");
        return meal -> meal.getName().equalsIgnoreCase(name);
    }

    private Predicate<Meal> searchByType() {
        System.out.println("Available meal types: Vegetarian, Non-Vegetarian, Vegan");
        String type = inputValidator.getValidString("Select meal type: ");
        return meal -> meal.getTypes().stream().anyMatch(t -> t.equalsIgnoreCase(type));
    }

    private Predicate<Meal> searchByMealsPerDay() {
        int mealsPerDay = inputValidator.getValidIntInRange("Enter meals per day (1-6): ", 1, 6);
        return meal -> meal.hasNMeals(mealsPerDay);
    }

    private Predicate<Meal> searchByCostPerDay() {
        double maxCost = inputValidator.getValidPositiveDouble("Enter maximum cost per day: ");
        return meal -> meal.getCostPerDay() <= maxCost;
    }
}
