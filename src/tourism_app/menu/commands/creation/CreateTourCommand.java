package tourism_app.menu.commands.creation;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.menu.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.TourType;
import tourism_app.tour.location.Location;
import tourism_app.tour.meal.Meal;
import tourism_app.tour.transport.Transport;
import tourism_app.services.utils.InputValidator;

import java.util.Date;

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

        System.out.print("Enter tour name: ");
        String name = inputValidator.getValidString();

        Location location = selectLocation();

        System.out.println("Enter tour type (e.g., FAMILY, ADVENTURE): ");
        TourType type;
        while (true) {
            try {
                type = TourType.valueOf(inputValidator.getValidString().toUpperCase());
                break;
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type. Please try again.");
            }
        }

        System.out.print("Enter tour price: ");
        double price = inputValidator.getValidPositiveDouble();

        Date startDate = inputValidator.getValidDate("yyyy-MM-dd");

        Date endDate;
        while (true) {
            System.out.println("Enter end date (must be after start date): ");
            endDate = inputValidator.getValidDate("yyyy-MM-dd");
            if (endDate.after(startDate)) {
                break;
            }
            System.out.println("End date must be after start date. Try again.");
        }

        Meal meal = selectMeal();

        Transport transport = selectTransport();

        System.out.print("Enter markup percentage: ");
        double markUp = inputValidator.getValidPositiveDouble();

        Tour newTour = new Tour(name, location, meal, transport, startDate, endDate, type, markUp);
        dbManager.getTourDatabase().addTour(newTour);

        System.out.println("Tour created successfully!");
    }

    private Location selectLocation() {
        System.out.println("Choose a location:");
        dbManager.getLocationDatabase().listAllLocations();
        System.out.println("0 - Create a new location");

        int locationChoice = inputValidator.getValidIntInRange(0,
                dbManager.getLocationDatabase().getLocations().size());

        if (locationChoice == 0) {
            new CreateLocationCommand(dbManager.getLocationDatabase(), inputValidator).execute();
            return dbManager.getLocationDatabase().getLocations().get
                    (dbManager.getLocationDatabase().getLocations().size() - 1);
        } else {
            return (Location) dbManager.getLocationDatabase().getLocations().values().toArray()[locationChoice - 1];
        }
    }

    private Meal selectMeal() {
        System.out.println("Choose a meal:");
        dbManager.getMealDatabase().listAllMeals();
        System.out.println("0 - Create a new meal");

        int mealChoice = inputValidator.getValidIntInRange(0,
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
        System.out.println("Choose a transport:");
        dbManager.getTransportDatabase().listAllTransports();
        System.out.println("0 - Create a new transport");

        int transportChoice = inputValidator.getValidIntInRange(0,
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
