package tourism_app.services.lib;

import tourism_app.tour.meal.Meal;

public class MealDatabase extends AbstractDatabase<Meal> {
    public MealDatabase() {
        super("meals.ser");
    }
}
