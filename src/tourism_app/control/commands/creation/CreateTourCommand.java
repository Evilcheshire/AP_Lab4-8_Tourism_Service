package tourism_app.control.commands.creation;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.TourType;
import tourism_app.tour.location.Location;
import tourism_app.tour.location.LocationType;
import tourism_app.tour.meal.Meal;
import tourism_app.tour.transport.Transport;
import tourism_app.services.utils.InputValidator;

import java.util.Date;
import java.util.List;

public class CreateTourCommand implements Command {
    private final DatabaseManager dbManager;
    private final InputValidator inputValidator;

    public CreateTourCommand(DatabaseManager dbManager, InputValidator inputValidator) {
        this.dbManager = dbManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        System.out.println("Creating a new tour...");

        String name = inputValidator.getValidString("Enter tour name: ");

        TourType[] types = TourType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].NAME + " (Base Price: " + types[i].BASE_PRICE + ")");
        }
        int choice = inputValidator.getValidIntInRange("Choose a tour type:", 1, types.length);
        TourType type = types[choice - 1];

        Location location = selectLocation(type);

        System.out.print("Enter start date(yyyy-MM-dd): ");
        Date startDate = inputValidator.getValidDate("yyyy-MM-dd");

        Date endDate;
        while (true) {
            System.out.print("Enter end date (must be after start date): ");
            endDate = inputValidator.getValidDate("yyyy-MM-dd");
            if (endDate.after(startDate)) {
                break;
            }
            System.out.println("End date must be after start date. Try again.");
        }

        Meal meal = selectMeal();
        Transport transport = selectTransport();
        double markUp = inputValidator.getValidPositiveDouble("Enter markup percentage: ");

        Tour newTour = new Tour(name, location, meal, transport, startDate, endDate, type, markUp);
        dbManager.getTourDatabase().addTour(newTour);

        System.out.println("Tour created successfully!");
    }

    private Location selectLocation(TourType selectedTourType) {
        List<LocationType> allowedLocationTypes = selectedTourType.getAllowedLocationTypes();

        System.out.println();
        List<Location> filteredLocations = dbManager.getLocationDatabase().getFilteredLocations(allowedLocationTypes);
        for (int i = 0; i < filteredLocations.size(); i++) {
            System.out.println((i + 1) + ". " + filteredLocations.get(i).toString());
        }
        System.out.println("0. Create new location");

        int locationChoice = inputValidator.getValidIntInRange("Choose a location:", 0, filteredLocations.size());

        if (locationChoice == 0) {
            new CreateLocationCommand(dbManager.getLocationDatabase(), inputValidator, allowedLocationTypes).execute();
            return dbManager.getLocationDatabase().getLocations()
                    .get(dbManager.getLocationDatabase().getLocations().size() - 1);
        } else {
            return filteredLocations.get(locationChoice - 1);
        }
    }

    private Meal selectMeal() {
        dbManager.getMealDatabase().listAllMeals();
        System.out.println("0 - Create a new meal");

        int mealChoice = inputValidator.getValidIntInRange("Choose a meal:",0,
                dbManager.getMealDatabase().getMeals().size());

        if (mealChoice == 0) {
            new CreateMealCommand(dbManager.getMealDatabase(), inputValidator).execute();
            return dbManager.getMealDatabase().getMeals().get
                    (dbManager.getMealDatabase().getMeals().size() - 1);
        } else {
            return (Meal) dbManager.getMealDatabase().getMeals().values().toArray()[mealChoice - 1];
        }
    }

    private Transport selectTransport() {
        dbManager.getTransportDatabase().listAllTransports();
        System.out.println("0 - Create a new transport");

        int transportChoice = inputValidator.getValidIntInRange("Choose a transport:", 0,
                dbManager.getTransportDatabase().getTransports().size());

        if (transportChoice == 0) {
            new CreateTransportCommand(dbManager.getTransportDatabase(), inputValidator).execute();
            return dbManager.getTransportDatabase().getTransports().get
                    (dbManager.getTransportDatabase().getTransports().size() - 1);
        } else {
            return (Transport) dbManager.getTransportDatabase().getTransports().values().toArray()[transportChoice - 1];
        }
    }

    @Override
    public String getName() {
        return "Create a new tour";
    }
}
