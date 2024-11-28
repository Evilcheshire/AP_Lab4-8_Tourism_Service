package tourism_app.control.commands.creation;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.control.commands.Command;
import tourism_app.tour.location.*;
import tourism_app.services.utils.InputValidator;

import java.util.Arrays;
import java.util.List;

public class CreateLocationCommand implements Command {
    private final LocationDatabase locationDB;
    private final InputValidator inputValidator;
    private final List<LocationType> allowedTypes;

    public CreateLocationCommand(LocationDatabase locationDB, InputValidator inputValidator) {
        this(locationDB, inputValidator, Arrays.asList(LocationType.values()));
    }

    public CreateLocationCommand(LocationDatabase locationDB, InputValidator inputValidator, List<LocationType> allowedTypes) {
        this.locationDB = locationDB;
        this.inputValidator = inputValidator;
        this.allowedTypes = allowedTypes;
    }

    @Override
    public void execute() {
        System.out.println("Creating a new location:");

        String name = inputValidator.getValidString("Enter the location name: ");

        if (locationDB.getLocations().containsKey(name)) {
            System.out.println("A location with this name already exists.");
            return;
        }

        String country = inputValidator.getValidString("Enter the country: ");

        for (int i = 0; i < allowedTypes.size(); i++) {
            System.out.println((i + 1) + ". " + allowedTypes.get(i).NAME);
        }
        int typeIndex = inputValidator.getValidIntInRange("Select the location type:",1, allowedTypes.size());
        LocationType type = allowedTypes.get(typeIndex - 1);

        String description = inputValidator.getValidString("Enter a description of the location: ");

        Location newLocation = new Location(name, country, type, description);

        locationDB.addLocation(newLocation);

        System.out.println("Location \"" + name + "\" has been successfully created!");
        System.out.println(newLocation);
    }

    @Override
    public String getName() {
        return "Create a new location";
    }
}
