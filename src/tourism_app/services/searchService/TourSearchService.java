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
        addSearchCriterion("name", this::searchByName);
        addSearchCriterion("location", this::searchByLocation);
        addSearchCriterion("type", this::searchByType);
        addSearchCriterion("start date", this::searchByStartDate);
        addSearchCriterion("price", this::searchByPrice);
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
        String type = inputValidator.getValidString("Enter the tour type (e.g., Rest, Excursion): ");
        try {
            TourType tourType = TourType.valueOf(type.toUpperCase());
            return tour.getType() == tourType;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid tour type entered.");
            return false;
        }
    }

    private boolean searchByStartDate(Tour tour) {
        Date minDate = inputValidator.getValidDate("yyyy-MM-dd");
        Date maxDate = inputValidator.getValidDate("yyyy-MM-dd");

        if (maxDate.before(minDate)) {
            System.out.println("End date cannot be earlier than start date. Please try again.");
            return searchByStartDate(tour);
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
}
