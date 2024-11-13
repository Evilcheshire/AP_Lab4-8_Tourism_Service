package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;
import tourism_app.users.User;

import java.util.Scanner;

public class RegisterUserCommand implements Command, AuthCommand {
    private final UserDatabase userDatabase;
    private User registeredUser;

    public RegisterUserCommand(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public User getAuthenticatedUser() {
        return registeredUser;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        registeredUser = userDatabase.registerUser(name, password);
        System.out.println("Registration successful! You can now log in.");
    }

    public String getName() {
        return "Register user";
    }
}
