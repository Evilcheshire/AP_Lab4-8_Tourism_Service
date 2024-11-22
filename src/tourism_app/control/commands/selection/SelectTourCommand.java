package tourism_app.control.commands.selection;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.services.searchService.TourSearchService;
import tourism_app.tour.Tour;
import tourism_app.users.User;
import tourism_app.users.UserWithTours;
import tourism_app.services.utils.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class SelectTourCommand implements Command {
    private final DatabaseManager dbManager;
    private final User user;
    private final InputValidator inputValidator;

    public SelectTourCommand(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        this.dbManager = dbManager;
        this.user = user;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        TourSearchService searchService = new TourSearchService(inputValidator);

        System.out.println("Select a search criterion:");
        searchService.displayAvailableSearchFields();

        int field = inputValidator.getValidIntInRange(1, searchService.getSearchFields().size());

        List<Tour> tours = dbManager.getTourDatabase().getTours();
        List<Tour> searchResults = searchService.search(searchService.getSearchFields().get(field - 1), tours);

        if (searchResults.isEmpty()) {
            System.out.println("No tours found matching your criteria.");
            return;
        }

        System.out.println("Available tours:");
        for (int i = 0; i < searchResults.size(); i++) {
            System.out.println((i + 1) + ". " + searchResults.get(i).getName());
        }

        int selectedIndex = inputValidator.getValidIntInRange(1, searchResults.size());
        Tour selectedTour = searchResults.get(selectedIndex - 1);

        UserWithTours userWithTours = dbManager.getUserTourDatabase().getUserTours().computeIfAbsent(
                user.getID(), id -> new UserWithTours(user, new ArrayList<>())
        );

        if (userWithTours.getSelectedTours().contains(selectedTour)) {
            System.out.println("Tour already selected.");
        } else {
            userWithTours.addTour(selectedTour);
            dbManager.getUserTourDatabase().addUserWithTours(userWithTours);
            System.out.println("Tour successfully selected: " + selectedTour.getName());
        }
    }

    @Override
    public String getName() {
        return "Select tour";
    }
}
