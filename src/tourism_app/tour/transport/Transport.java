package tourism_app.tour.transport;

import java.io.Serializable;

public class Transport implements Serializable {
    private String name;
    private TransportType type;
    private double cost;

    public Transport(String name, TransportType type, double cost){
        this.name = name;
        this.type = type;
        this.cost = cost;
    }

    public String getName() {
        return name;
    }

    public TransportType getType() {
        return type;
    }

    public double getCost() {
        return cost;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setType(TransportType type) {
        this.type = type;
    }

    public void setCost(double cost) {
        this.cost = cost;
    }

    public String toString() {
        return String.format("%s\nType: %s\nCost per Day: %.2f",
                name, type, cost);
    }



}
