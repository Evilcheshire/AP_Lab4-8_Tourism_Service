package tourism_app.control.commands.creation;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;
import tourism_app.users.*;
import tourism_app.services.utils.InputValidator;

public class CreateUserCommand implements Command {
    private final UserDatabase userDB;
    private final InputValidator inputValidator;

    public CreateUserCommand(UserDatabase userDB, InputValidator inputValidator) {
        this.userDB = userDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        System.out.println("Creating a new user:");

        String name = inputValidator.getValidString("Enter the username: ");

        if (userDB.getUserByName(name) != null) {
            System.out.println("A user with this name already exists.");
            return;
        }

        System.out.println("Select the user type:");
        UserType[] userTypes = UserType.values();
        for (int i = 0; i < userTypes.length; i++) {
            System.out.println((i + 1) + ". " + userTypes[i].getName());
        }

        int typeIndex = inputValidator.getValidIntInRange("Enter the number of the user type: ",1, userTypes.length);
        UserType selectedType = userTypes[typeIndex - 1];

        String password = inputValidator.getValidString("Enter the password: ");

        User newUser = userDB.registerUser(name, password, selectedType);

        System.out.println("New  user \"" + name + "\" created successfully!");
        System.out.println(newUser);
    }

    @Override
    public String getName() {
        return "Create a new user";
    }
}
