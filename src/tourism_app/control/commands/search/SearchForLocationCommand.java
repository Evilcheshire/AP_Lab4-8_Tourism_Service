package tourism_app.control.commands.search;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.services.searchService.LocationSearchService;
import tourism_app.control.commands.Command;
import tourism_app.services.utils.InputValidator;
import tourism_app.tour.location.Location;

import java.util.ArrayList;
import java.util.List;

public class SearchForLocationCommand implements Command {
    private final LocationDatabase locationDatabase;
    private final LocationSearchService searchService;
    private final InputValidator inputValidator;

    public SearchForLocationCommand(LocationDatabase locationDatabase, InputValidator inputValidator) {
        this.locationDatabase = locationDatabase;
        this.inputValidator = inputValidator;
        this.searchService = new LocationSearchService(inputValidator);
    }

    @Override
    public void execute() {
        searchService.displayAvailableSearchFields();

        int field = inputValidator.getValidIntInRange(1, searchService.getSearchFields().size());

        List<Location> results = searchService.search(searchService.getSearchFields().get(field),
                new ArrayList<>(locationDatabase.getLocations().values()));

        if (results.isEmpty()) {
            System.out.println("No locations found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
        return "Search for location";
    }
}
