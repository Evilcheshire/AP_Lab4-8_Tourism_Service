package tourism_app.control.commands.authentification;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;
import tourism_app.services.utils.InputValidator;
import tourism_app.users.User;

public class LoginCommand implements Command{
    private final UserDatabase userDatabase;
    private User authenticatedUser;
    private final InputValidator inputValidator;

    public LoginCommand(UserDatabase userDatabase, InputValidator inputValidator) {
        this.userDatabase = userDatabase;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        String username = inputValidator.getValidString("Enter username: ");
        String password = inputValidator.getValidString("Enter password: ");

        authenticatedUser = userDatabase.login(username, password);

        if (authenticatedUser != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    @Override
    public User getAuthUser() {
        return authenticatedUser;
    }

    @Override
    public String getName() {
        return "Log in";
    }
}