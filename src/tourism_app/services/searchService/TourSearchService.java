package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.Tour;

import java.util.Date;
import java.util.function.Predicate;

public class TourSearchService extends SearchService<Tour> {
    public TourSearchService(InputValidator inputValidator) {
        super(inputValidator);
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("Name", this::searchByName);
        addSearchCriterion("Location", this::searchByLocation);
        addSearchCriterion("Type", this::searchByType);
        addSearchCriterion("Date Range", this::searchByDateRange);
        addSearchCriterion("Price", this::searchByPrice);
        addSearchCriterion("Meal Type", this::searchByMealType);
        addSearchCriterion("Transport Type", this::searchByTransportType);
    }

    private Predicate<Tour> searchByName() {
        String name = inputValidator.getValidString("Enter tour name: ");
        return tour -> tour.getName().equalsIgnoreCase(name);
    }

    private Predicate<Tour> searchByLocation() {
        String locationName = inputValidator.getValidString("Enter location name: ");
        return tour -> tour.getLocation().getName().equalsIgnoreCase(locationName);
    }

    private Predicate<Tour> searchByType() {
        String type = inputValidator.getValidString("Enter tour type: ");
        return tour -> tour.getType().NAME.equalsIgnoreCase(type);
    }

    private Predicate<Tour> searchByDateRange() {
        Date startDate = inputValidator.getValidDate("Enter start date (yyyy-MM-dd): ");
        Date endDate = inputValidator.getValidDate("Enter end date (yyyy-MM-dd): ");
        return tour -> !tour.getStartDate().before(startDate) && !tour.getEndDate().after(endDate);
    }

    private Predicate<Tour> searchByPrice() {
        double maxPrice = inputValidator.getValidPositiveDouble("Enter maximum price: ");
        return tour -> tour.getTotalPrice() <= maxPrice;
    }

    private Predicate<Tour> searchByMealType() {
        System.out.println("Available meal types: Vegetarian, Non-Vegetarian, Vegan");
        String mealType = inputValidator.getValidString("Select meal type: ");
        return tour -> tour.getMeal().getTypes().stream().anyMatch(type -> type.equalsIgnoreCase(mealType));
    }

    private Predicate<Tour> searchByTransportType() {
        System.out.println("Available transport types: Bus, Train, Plane");
        String transportType = inputValidator.getValidString("Select transport type: ");
        return tour -> tour.getTransport().getType().NAME.equalsIgnoreCase(transportType);
    }
}
