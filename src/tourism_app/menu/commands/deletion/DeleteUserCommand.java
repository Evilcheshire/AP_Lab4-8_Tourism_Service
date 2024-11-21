package tourism_app.menu.commands.deletion;

import tourism_app.services.lib.UserDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.Map;

public class DeleteUserCommand implements Command {
    private final UserDatabase userDB;
    private final InputValidator inputValidator;

    public DeleteUserCommand(UserDatabase userDB, InputValidator inputValidator) {
        this.userDB = userDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<Integer, User> users = userDB.getUsersById();

        if (users.isEmpty()) {
            System.out.println("No users available to delete.");
            return;
        }

        System.out.println("Available users to delete:");
        userDB.listAllUsers();

        System.out.println("Enter the ID of the user to delete:");
        int userId = inputValidator.getValidInt();

        User userToDelete = userDB.getUserById(userId);

        if (userToDelete == null) {
            System.out.println("No user found with ID: " + userId);
            return;
        }

        if (userDB.removeUser(userToDelete.getName())) {
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
