package tourism_app.menu.commands.selection;

import tourism_app.services.lib.UserTourDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.users.User;
import tourism_app.users.UserWithTours;
import tourism_app.services.utils.InputValidator;

public class UnselectTourCommand implements Command {
    private final UserTourDatabase userTourDatabase;
    private final User user;
    private final InputValidator inputValidator;

    public UnselectTourCommand(UserTourDatabase userTourDatabase, User user, InputValidator inputValidator) {
        this.userTourDatabase = userTourDatabase;
        this.user = user;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        String tourName = inputValidator.getValidString("Enter the name of the tour you want to remove:");

        UserWithTours userWithTours = userTourDatabase.getUserTours().get(user.getID());
        if (userWithTours == null || userWithTours.getSelectedTours().isEmpty()) {
            System.out.println("You have no selected tours to remove.");
            return;
        }

        Tour tourToRemove = null;
        for (Tour tour : userWithTours.getSelectedTours()) {
            if (tour.getName().equalsIgnoreCase(tourName)) {
                tourToRemove = tour;
                break;
            }
        }

        if (tourToRemove == null) {
            System.out.println("Tour not found in your selected tours.");
        } else {
            userWithTours.removeTour(tourToRemove);
            userTourDatabase.addUserWithTours(userWithTours); // Update the database
            System.out.println("Tour successfully removed: " + tourToRemove.getName());
        }
    }

    @Override
    public String getName() {
        return "Unselect tour";
    }
}
