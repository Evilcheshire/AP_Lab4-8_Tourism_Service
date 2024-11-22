package tourism_app.control.commands.view;

import tourism_app.services.lib.MealDatabase;
import tourism_app.control.commands.Command;

public class ViewAllMealsCommand implements Command {
    private final MealDatabase mealDB;

    public ViewAllMealsCommand(MealDatabase mealDB){
        this.mealDB = mealDB;
    }

    public void execute() {
        System.out.println("Available meals:");
        mealDB.listAllMeals();
    }
    public String getName(){
        return "View all meals";
    }
}
