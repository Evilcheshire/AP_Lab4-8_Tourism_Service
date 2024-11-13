package tourism_app.tour.meal;

import java.io.Serializable;
import java.util.List;

public class Meal  implements Serializable {
    private List<String> types;
    private double costPerDay;
    private int mealsPerDay;

    public Meal(List<String> types, double costPerDay, int mealsPerDay){
        this.types = types;
        this.costPerDay = costPerDay;
        this.mealsPerDay = mealsPerDay;
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

}
