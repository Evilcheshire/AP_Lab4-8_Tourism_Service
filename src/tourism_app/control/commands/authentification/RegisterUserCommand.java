package tourism_app.control.commands.authentification;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;
import tourism_app.services.utils.InputValidator;
import tourism_app.users.User;
import tourism_app.users.UserType;

import java.util.Scanner;

public class RegisterUserCommand implements Command{
    private final UserDatabase userDatabase;
    private User registeredUser;
    private final InputValidator inputValidator;

    public RegisterUserCommand(UserDatabase userDatabase, InputValidator inputValidator) {
        this.userDatabase = userDatabase;
        this.inputValidator = inputValidator;
    }

    public void execute() {
        String username =inputValidator.getValidString("Enter username: \n\t->");
        String password =  inputValidator.getValidString("Enter password: \n\t->");


        registeredUser = userDatabase.registerUser(username, password, UserType.CUSTOMER);

        if (registeredUser == null) {
            System.out.println("Error: Registration failed. User with this name already exists.");
        } else {
            System.out.println("Registration successful!");
        }
    }

    @Override
    public User getAuthUser() {
        return registeredUser;
    }

    @Override
    public String getName() {
        return "Register";
    }
}
