package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.location.Location;

public class LocationSearchService extends SearchService<Location> {
    private final InputValidator inputValidator;

    public LocationSearchService(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("name", this::searchByName);
        addSearchCriterion("country", this::searchByCountry);
        addSearchCriterion("type", this::searchByType);
    }

    private boolean searchByName(Location location) {
        String name = inputValidator.getValidString("Enter name to search: ");
        return location.getName().equals(name);
    }

    private boolean searchByCountry(Location location) {
        String country = inputValidator.getValidString("Enter country to search: ");
        return location.getCountry().equalsIgnoreCase(country);
    }

    private boolean searchByType(Location location) {
        String type = inputValidator.getValidString("Enter location type (e.g., BEACH, MOUNTAIN): ");
        try {
            return location.getType().toString().equalsIgnoreCase(type);
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid type entered.");
            return false;
        }
    }
}
