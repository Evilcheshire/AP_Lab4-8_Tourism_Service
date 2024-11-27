package tourism_app.tour.meal;

import java.io.Serializable;
import java.util.List;

public class Meal  implements Serializable {
    private String name;
    private List<String> types;
    private double costPerDay;
    private int mealsPerDay;

    public Meal(String name, List<String> types, double costPerDay, int mealsPerDay){
        this.name = name;
        this.types = types;
        this.costPerDay = costPerDay;
        this.mealsPerDay = mealsPerDay;
    }

    public String getName() {
        return name;
    }

    public List<String> getTypes(){
        return types;
    }

    public double getCostPerDay(){
        return costPerDay;
    }

    public int getMealsPerDay() {
        return mealsPerDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTypes(List<String> types){
        this.types = types;
    }

    public void addTypes(List<String> types){
        this.types.addAll(types);
    }

    public void deleteType(String type){
        this.types.remove(type);
    }

    public void setCostPerDay(double costPerDay){
        this.costPerDay = costPerDay;
    }

    public void setMealsPerDay(int mealsPerDay) {
        this.mealsPerDay = mealsPerDay;
    }

    public boolean hasType(String type){
        return types.contains(type);
    }

    public boolean hasNMeals(int n) {
        return mealsPerDay == n;
    }

    public boolean hasMultipleMeals() {
        return mealsPerDay > 1;
    }

    public String toString() {
        return String.format("Meal: %s\n\t\tTypes: %s\n\t\tCost per Day: %.2f\n\t\tMeals per Day: %d",
                name, String.join(", ", types), costPerDay, mealsPerDay);
    }
}
