package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.Tour;
import tourism_app.tour.TourType;

import java.util.Date;
public class TourSearchService extends SearchService<Tour> {
    private final InputValidator inputValidator;

    public TourSearchService(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("Name", this::searchByName);
        addSearchCriterion("Location", this::searchByLocation);
        addSearchCriterion("Type", this::searchByType);
        addSearchCriterion("Date Range", this::searchByDateRange);
        addSearchCriterion("Price", this::searchByPrice);
        addSearchCriterion("Meal Type", this::searchByMealType);
    }

    private boolean searchByName(Tour tour) {
        String name = inputValidator.getValidString("Enter the tour name to search: ");
        return tour.getName().equals(name);
    }

    private boolean searchByLocation(Tour tour) {
        String location = inputValidator.getValidString("Enter the location name to search: ");
        return tour.getLocation().getName().equals(location);
    }

    private boolean searchByType(Tour tour) {
        System.out.println("Available tour types:");
        TourType[] types = TourType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.printf("%d. %s (%s)%n", i + 1, types[i].NAME, types[i].name());
        }
        System.out.println("Choose the number corresponding to the tour type: ");
        int choice = inputValidator.getValidIntInRange(1, types.length);
        TourType selectedType = types[choice - 1];
        return tour.getType() == selectedType;
    }

    private boolean searchByDateRange(Tour tour) {
        Date minDate = inputValidator.getValidDate("Enter the start date (yyyy-MM-dd): ");
        Date maxDate = inputValidator.getValidDate("Enter the end date (yyyy-MM-dd): ");

        while (maxDate.before(minDate)) {
            System.out.println("End date cannot be earlier than start date. Please try again.");
            minDate = inputValidator.getValidDate("Enter the start date (yyyy-MM-dd): ");
            maxDate = inputValidator.getValidDate("Enter the end date (yyyy-MM-dd): ");
        }
        return tour.getStartDate().compareTo(minDate) >= 0 && tour.getStartDate().compareTo(maxDate) <= 0;
    }

    private boolean searchByPrice(Tour tour) {
        double minPrice = inputValidator.getValidPositiveDouble();
        double maxPrice = inputValidator.getValidPositiveDouble();

        if (maxPrice < minPrice) {
            System.out.println("Maximum price cannot be less than minimum price. Please try again.");
            return searchByPrice(tour);
        }
        return tour.getTotalPrice() >= minPrice && tour.getTotalPrice() <= maxPrice;
    }

    private boolean searchByMealType(Tour tour) {
        String type = inputValidator.getValidString("Enter meal type (e.g., VEGETARIAN, NON_VEGETARIAN, VEGAN): ");
        return tour.getMeal().getTypes().stream().anyMatch(t -> t.equalsIgnoreCase(type));
    }
}
