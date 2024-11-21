package tourism_app.menu.commands.search;

import tourism_app.services.lib.TourDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForTourCommand implements Command {
    private final TourDatabase tourDatabase;
    private final InputValidator inputValidator;

    public SearchForTourCommand(TourDatabase tourDatabase, InputValidator inputValidator) {
        this.tourDatabase = tourDatabase;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        tourDatabase.displaySearchFields();
        int choice = inputValidator.getValidIntInRange(1, tourDatabase.getFieldOptionsSize());

        List<Tour> results = tourDatabase.searchTours(choice);

        if (results.isEmpty()) {
            System.out.println("No tours found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    public String getName() {
        return "Search for tour";
    }
}
