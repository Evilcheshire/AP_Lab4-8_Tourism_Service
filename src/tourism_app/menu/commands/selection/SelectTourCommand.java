package tourism_app.menu.commands.selection;

import tourism_app.services.lib.UserTourDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.users.User;
import tourism_app.users.UserWithTours;
import tourism_app.services.utils.InputValidator;

import java.util.ArrayList;

public class SelectTourCommand implements Command {
    private final UserTourDatabase userTourDatabase;
    private final User user;
    private final InputValidator inputValidator;

    public SelectTourCommand(UserTourDatabase userTourDatabase, User user, InputValidator inputValidator) {
        this.userTourDatabase = userTourDatabase;
        this.user = user;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        String tourName = inputValidator.getValidString("Enter the name of the tour you want to select:");

        Tour tour = userTourDatabase.getTourByName(tourName); // Replace with actual logic to fetch the tour
        if (tour == null) {
            System.out.println("Tour not found. Please try again.");
            return;
        }

        UserWithTours userWithTours = userTourDatabase.getUserTours().get(user.getID());
        if (userWithTours == null) {
            userWithTours = new UserWithTours(user, new ArrayList<>());
        }

        if (userWithTours.getSelectedTours().contains(tour)) {
            System.out.println("Tour already selected.");
        } else {
            userWithTours.addTour(tour);
            userTourDatabase.addUserWithTours(userWithTours);
            System.out.println("Tour successfully selected: " + tour.getName());
        }
    }

    @Override
    public String getName() {
        return "Select tour";
    }
}
