package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.location.Location;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class DeleteLocationCommand implements Command {
    private final DatabaseManager databaseManager;
    private final InputValidator inputValidator;

    public DeleteLocationCommand(DatabaseManager databaseManager, InputValidator inputValidator) {
        this.databaseManager = databaseManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        List<Location> locations = databaseManager.getLocationDatabase().getAllItemsAsList();

        if (locations.isEmpty()) {
            System.out.println("No locations available to delete.");
            return;
        }

        System.out.println("Available locations to delete:");
        databaseManager.getLocationDatabase().listAllItems();

        int choice = inputValidator.getValidIntInRange("Enter the number of the location to delete:", 1, locations.size());
        Location selectedLocation = locations.get(choice - 1);

        boolean isRemoved = databaseManager.getLocationDatabase().removeItem(selectedLocation.getName(), () -> {
            databaseManager.getTourDatabase().getAllItemsAsList().stream()
                    .filter(tour -> selectedLocation.equals(tour.getLocation()))
                    .forEach(tour -> {
                        databaseManager.getTourDatabase().removeItem(tour.getName(), () ->
                                System.out.println("Tour \"" + tour.getName() + "\" removed due to missing location."));
                        databaseManager.getUserTourDatabase().removeTourFromUsers(tour.getName());
                    });
        });

        if (isRemoved) {
            System.out.println("Location \"" + selectedLocation.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete location \"" + selectedLocation.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a location";
    }
}
