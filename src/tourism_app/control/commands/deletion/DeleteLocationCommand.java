package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.location.Location;
import tourism_app.services.utils.InputValidator;

import java.util.Map;
import java.util.stream.Collectors;

public class DeleteLocationCommand implements Command {
    private final DatabaseManager databaseManager;
    private final InputValidator inputValidator;

    public DeleteLocationCommand(DatabaseManager databaseManager, InputValidator inputValidator) {
        this.databaseManager = databaseManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<String, Location> locations = databaseManager.getLocationDatabase().getLocations();

        if (locations.isEmpty()) {
            System.out.println("No locations available to delete.");
            return;
        }

        System.out.println("Available locations to delete:");
        databaseManager.getLocationDatabase().listAllLocations();

        int choice = inputValidator.getValidIntInRange("Enter the number of the location to delete:",1, locations.size());

        String selectedLocationName = (String) locations.keySet().toArray()[choice - 1];
        Location selectedLocation = locations.get(selectedLocationName);

        if (databaseManager.getLocationDatabase().removeLocation(selectedLocationName)) {
            System.out.println("Location \"" + selectedLocation.getName() + "\" has been deleted.");

            databaseManager.getTourDatabase().getTours().stream()
                    .filter(tour -> selectedLocation.equals(tour.getLocation()))
                    .collect(Collectors.toList())
                    .forEach(tour -> {
                        databaseManager.getTourDatabase().removeTour(tour.getName(), () ->
                                System.out.println("Tour \"" + tour.getName() + "\" removed due to missing location."));
                        databaseManager.getUserTourDatabase().removeTourFromUsers(tour.getName());
                    });

            databaseManager.saveAllDatabases();
        } else {
            System.out.println("Failed to delete location \"" + selectedLocation.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a location";
    }
}
