package tourism_app.menu.commands.search;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.location.Location;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForLocationCommand implements Command {
    private final LocationDatabase locationDatabase;
    private final InputValidator inputValidator;

    public SearchForLocationCommand(LocationDatabase locationDatabase, InputValidator inputValidator) {
        this.locationDatabase = locationDatabase;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        locationDatabase.displaySearchFields();
        int choice = inputValidator.getValidIntInRange(1, locationDatabase.getFieldOptionsSize());

        List<Location> results = locationDatabase.searchLocations(choice);

        if (results.isEmpty()) {
            System.out.println("No locations found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    @Override
    public String getName(){
        return "Search for location";
    }
}

