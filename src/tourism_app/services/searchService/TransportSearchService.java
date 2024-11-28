package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.transport.Transport;

import java.util.function.Predicate;

public class TransportSearchService extends SearchService<Transport> {
    public TransportSearchService(InputValidator inputValidator) {
        super(inputValidator);
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("Name", this::searchByName);
        addSearchCriterion("Type", this::searchByType);
        addSearchCriterion("Cost per Day", this::searchByCostPerDay);
    }

    private Predicate<Transport> searchByName() {
        String name = inputValidator.getValidString("Enter transport name: ");
        return transport -> transport.getName().equalsIgnoreCase(name);
    }

    private Predicate<Transport> searchByType() {
        System.out.println("Available transport types: Bus, Train, Plane");
        String type = inputValidator.getValidString("Select transport type: ");
        return transport -> transport.getType().NAME.equalsIgnoreCase(type);
    }

    private Predicate<Transport> searchByCostPerDay() {
        double maxCost = inputValidator.getValidPositiveDouble("Enter maximum cost per day: ");
        return transport -> transport.getCost() <= maxCost;
    }
}
