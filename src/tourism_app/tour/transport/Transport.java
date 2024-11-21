package tourism_app.tour.transport;

import java.io.Serializable;

public class Transport implements Serializable {
    private String name;
    private TransportType type;
    private double costPerDay;

    public Transport(String name, TransportType type, double costPerDay){
        this.name = name;
        this.type = type;
        this.costPerDay = costPerDay;
    }

    public String getName() {
        return name;
    }

    public TransportType getType() {
        return type;
    }

    public double getCostPerDay() {
        return costPerDay;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public void setCostPerDay(double costPerDay) {
        this.costPerDay = costPerDay;
    }

    public String toString() {
        return String.format("Transport: %s\nType: %s\nCost per Day: %.2f",
                name, type, costPerDay);
    }



}
