package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.tour.transport.Transport;
import tourism_app.tour.transport.TransportType;

public class TransportSearchService extends SearchService<Transport> {
    private final InputValidator inputValidator;

    public TransportSearchService(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("name", this::searchByName);
        addSearchCriterion("type", this::searchByType);
        addSearchCriterion("costPerDay", this::searchByCostPerDay);
    }

    private boolean searchByName(Transport transport) {
        String name = inputValidator.getValidString("Enter transport name to search: ");
        return transport.getName().equals(name);
    }

    private boolean searchByType(Transport transport) {
        String type = inputValidator.getValidString("Enter transport type (Bus, Train, Plane, Cruise ship): ");
        try {
            TransportType transportType = TransportType.valueOf(type.toUpperCase());
            return transport.getType() == transportType;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid transport type.");
            return false;
        }
    }

    private boolean searchByCostPerDay(Transport transport) {
        double maxCost = inputValidator.getValidPositiveDouble();
        return transport.getCost() <= maxCost;
    }
}
