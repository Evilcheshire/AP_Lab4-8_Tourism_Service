package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.Map;

public class DeleteUserCommand implements Command {
    private final DatabaseManager dbManager;
    private final InputValidator inputValidator;

    public DeleteUserCommand(DatabaseManager dbManager, InputValidator inputValidator) {
        this.dbManager = dbManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<Integer, User> users = dbManager.getUserDatabase().getUsersById();

        if (users.isEmpty()) {
            System.out.println("No users available to delete.");
            return;
        }

        System.out.println("Available users to delete:");
        dbManager.getUserDatabase().listAllUsers();

        System.out.println("Enter the ID of the user to delete:");
        int userId = inputValidator.getValidInt();

        User userToDelete = dbManager.getUserDatabase().getUserById(userId);

        if (userToDelete == null) {
            System.out.println("No user found with ID: " + userId);
            return;
        }

        if (userToDelete.isCustomer()) {
            if (dbManager.getUserTourDatabase().removeToursForUser(userId)) {
                System.out.println("All selected tours for user \"" + userToDelete.getName() + "\" have been removed.");
            } else {
                System.out.println("No selected tours found for user \"" + userToDelete.getName() + "\".");
            }
        }

        if (dbManager.getUserDatabase().removeUser(userToDelete.getName())) {
            System.out.println("User \"" + userToDelete.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete user \"" + userToDelete.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a user";
    }
}
