package tourism_app.menu.commands.deletion;

import tourism_app.services.lib.TourDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class DeleteTourCommand implements Command {
    private final TourDatabase tourDB;
    private final InputValidator inputValidator;

    public DeleteTourCommand(TourDatabase tourDB, InputValidator inputValidator) {
        this.tourDB = tourDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        List<Tour> tours = tourDB.getTours();

        if (tours.isEmpty()) {
            System.out.println("No tours available to delete.");
            return;
        }

        System.out.println("Available tours to delete:");
        tourDB.listAllTours();

        System.out.println("Enter the number of the location to delete:");
        int choice = inputValidator.getValidIntInRange(1, tours.size() + 1);

        Tour selectedTour = tours.get(choice - 1);

        if (tourDB.removeTour(selectedTour.getName())) {
            System.out.println("Tour \"" + selectedTour.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete tour \"" + selectedTour.getName() + "\".");
        }
    }

    public String getName() {
        return "Delete tour";
    }
}
