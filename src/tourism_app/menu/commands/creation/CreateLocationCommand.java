package tourism_app.menu.commands.creation;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.location.*;
import tourism_app.services.utils.InputValidator;

public class CreateLocationCommand implements Command {
    private final LocationDatabase locationDB;
    private final InputValidator inputValidator;

    public CreateLocationCommand(LocationDatabase locationDB, InputValidator inputValidator) {
        this.locationDB = locationDB;
        this.inputValidator = inputValidator;
    }

    public void execute() {
        System.out.println("Creating a new location:");

        System.out.print("Enter the location name: ");
        String name = inputValidator.getValidString();

        if (locationDB.getLocations().containsKey(name)) {
            System.out.println("A location with this name already exists.");
            return;
        }

        System.out.print("Enter the country: ");
        String country = inputValidator.getValidString();

        System.out.println("Select the location type:");
        LocationType[] types = LocationType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].NAME);
        }
        System.out.print("Enter the number of the type: ");
        int typeIndex = inputValidator.getValidIntInRange(1, types.length);
        LocationType type = types[typeIndex - 1];

        System.out.print("Enter a description of the location: ");
        String description = inputValidator.getValidString();

        Location newLocation = new Location(name, country, type, description);

        locationDB.addLocation(newLocation);

        System.out.println("Location \"" + name + "\" has been successfully created!");
        System.out.println(newLocation);
    }

    public String getName() {
        return "Create Location";
    }
}