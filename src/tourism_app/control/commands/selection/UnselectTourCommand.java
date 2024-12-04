package tourism_app.control.commands.selection;

import tourism_app.services.lib.UserTourDatabase;
import tourism_app.control.commands.Command;
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
        UserWithTours userWithTours = userTourDatabase.getAllItemsAsMap().get(user.getName());
        if (userWithTours == null || userWithTours.getSelectedTours().isEmpty()) {
            System.out.println("You have no selected tours to remove.");
            return;
        }

        System.out.println("Your selected tours:");
        for (int i = 0; i < userWithTours.getSelectedTours().size(); i++) {
            System.out.println((i + 1) + ". " + userWithTours.getSelectedTours().get(i).getName());
        }

        int choice = inputValidator.getValidIntInRange("Enter the number of tour to be removed",1, userWithTours.getSelectedTours().size());
        Tour tourToRemove = userWithTours.getSelectedTours().get(choice - 1);

        userWithTours.removeTour(tourToRemove);
        System.out.println("Tour successfully removed: " + tourToRemove.getName());
    }

    @Override
    public String getName() {
        return "Unselect tour";
    }
}
