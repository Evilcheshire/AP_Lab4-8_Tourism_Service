package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.location.Location;
import tourism_app.tour.location.LocationType;

import java.util.function.Predicate;

public class LocationSearchService extends SearchService<Location> {
    public LocationSearchService(InputValidator inputValidator) {
        super(inputValidator);
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("Name", this::searchByName);
        addSearchCriterion("Country", this::searchByCountry);
        addSearchCriterion("Type", this::searchByType);
    }

    private Predicate<Location> searchByName() {
        String name = inputValidator.getValidString("Enter location name: ");
        return location -> location.getName().equalsIgnoreCase(name);
    }

    private Predicate<Location> searchByCountry() {
        String country = inputValidator.getValidString("Enter country: ");
        return location -> location.getCountry().equalsIgnoreCase(country);
    }

    private Predicate<Location> searchByType() {
        System.out.println("Available location types:");
        for (LocationType type : LocationType.values()) {
            System.out.println("- " + type.NAME);
        }
        String type = inputValidator.getValidString("Select location type: ");
        return location -> location.getType().NAME.equalsIgnoreCase(type);
    }
}
