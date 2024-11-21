package tourism_app.menu.commands.deletion;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.location.Location;
import tourism_app.services.utils.InputValidator;

import java.util.Map;

public class DeleteLocationCommand implements Command {
    private final LocationDatabase locationDB;
    private final InputValidator inputValidator;

    public DeleteLocationCommand(LocationDatabase locationDB, InputValidator inputValidator) {
        this.locationDB = locationDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<String, Location> locations = locationDB.getLocations();

        if (locations.isEmpty()) {
            System.out.println("No locations available to delete.");
            return;
        }

        System.out.println("Available locations to delete:");
        locationDB.listAllLocations();

        System.out.println("Enter the number of the location to delete:");
        int choice = inputValidator.getValidIntInRange(1, locations.size() + 1);

        String selectedLocationName = (String) locations.keySet().toArray()[choice - 1];
        Location selectedLocation = locations.get(selectedLocationName);

        if (locationDB.removeLocation(selectedLocationName)) {
            System.out.println("Location \"" + selectedLocation.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete location \"" + selectedLocation.getName() + "\".");
        }
    }

    public String getName() {
        return "Delete Location";
    }

}
