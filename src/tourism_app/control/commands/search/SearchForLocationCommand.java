package tourism_app.control.commands.search;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.services.searchService.LocationSearchService;
import tourism_app.control.commands.Command;
import tourism_app.tour.location.Location;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForLocationCommand implements Command {
    private final LocationDatabase locationDatabase;
    private final LocationSearchService searchService;

    public SearchForLocationCommand(LocationDatabase locationDatabase, InputValidator inputValidator) {
        this.locationDatabase = locationDatabase;
        this.searchService = new LocationSearchService(inputValidator);
    }

    @Override
    public void execute() {
        List<Location> locations = locationDatabase.getAllItemsAsList();
        List<Location> results = searchService.search(locations);

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
