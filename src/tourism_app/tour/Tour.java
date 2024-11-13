package tourism_app.tour;

import tourism_app.tour.location.Location;
import tourism_app.tour.meal.Meal;
import tourism_app.tour.transport.Transport;

import java.io.Serializable;
import java.util.Date;

public class Tour implements Serializable {
    private String name;
    private Location location;
    private Meal meal;
    private Transport transport;
    private Date startDate;
    private Date endDate;
    private TourType type;
    private double markUp;
    private double totalPrice;

    public Tour(String name, Location location, Meal meal, Transport transport, Date startDate,
                Date endDate, TourType type, double markUp){
        this.name = name;
        this.location = location;
        this.meal = meal;
        this.transport = transport;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.markUp = markUp;
        this.totalPrice = CalculateTotalPrice();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public Meal getMeal() {
        return meal;
    }

    public void setMeal(Meal meal) {
        this.meal = meal;
    }

    public Transport getTransport() {
        return transport;
    }

    public void setTransport(Transport transport) {
        this.transport = transport;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public TourType getType() {
        return type;
    }

    public void setType(TourType type) {
        this.type = type;
    }

    public double getMarkUp() {
        return markUp;
    }

    public void setMarkUp(double markUp) {
        this.markUp = markUp;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public long getDuration(){
        return (endDate.getTime() - startDate.getTime()) / (1000 * 60 * 60 * 24);
    }

    public double CalculateTotalPrice(){
        return (meal.getCostPerDay() + transport.getCostPerDay()) * getDuration() +
                markUp + type.BASE_PRICE;
    }
}

