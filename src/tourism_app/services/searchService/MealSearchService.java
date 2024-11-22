package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.meal.Meal;

public class MealSearchService extends SearchService<Meal> {
    private final InputValidator inputValidator;

    public MealSearchService(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("name", this::searchByName);
        addSearchCriterion("type", this::searchByType);
        addSearchCriterion("mealsPerDay", this::searchByMealsPerDay);
        addSearchCriterion("costPerDay", this::searchByCostPerDay);
    }

    private boolean searchByName(Meal meal) {
        String name = inputValidator.getValidString("Enter meal name to search: ");
        return meal.getName().equals(name);
    }

    private boolean searchByType(Meal meal) {
        String type = inputValidator.getValidString("Enter meal type (e.g., VEGETARIAN, NON_VEGETARIAN, VEGAN): ");
        return meal.getTypes().stream().anyMatch(t -> t.equalsIgnoreCase(type));
    }

    private boolean searchByMealsPerDay(Meal meal) {
        int mealsPerDay = inputValidator.getValidIntInRange(1, 6);
        return meal.hasNMeals(mealsPerDay);
    }

    private boolean searchByCostPerDay(Meal meal) {
        double maxCost = inputValidator.getValidPositiveDouble();
        return meal.getCostPerDay() <= maxCost;
    }
}
