package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class DeleteTourCommand implements Command {
    private final DatabaseManager dbManager;
    private final InputValidator inputValidator;

    public DeleteTourCommand(DatabaseManager dbManager, InputValidator inputValidator) {
        this.dbManager = dbManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        List<Tour> tours = dbManager.getTourDatabase().getTours();

        if (tours.isEmpty()) {
            System.out.println("No tours available to delete.");
            return;
        }

        System.out.println("Available tours to delete:");
        dbManager.getTourDatabase().listAllTours();

        System.out.println("Enter the number of the tour to delete:");
        int choice = inputValidator.getValidIntInRange(1, tours.size());

        Tour selectedTour = tours.get(choice - 1);

        boolean isRemoved = dbManager.getTourDatabase().removeTour(selectedTour.getName(), () -> {
            dbManager.getUserTourDatabase().getUserTours().values().forEach(userWithTours -> {
                userWithTours.getSelectedTours().removeIf(tour -> tour.getName().equals(selectedTour.getName()));
            });
            dbManager.getUserTourDatabase().saveToFile();
        });

        if (isRemoved) {
            System.out.println("Tour \"" + selectedTour.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete tour \"" + selectedTour.getName() + "\".");
        }
    }

    public String getName() {
        return "Delete a tour";
    }
}