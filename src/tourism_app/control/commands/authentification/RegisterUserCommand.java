package tourism_app.control.commands.authentification;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;
import tourism_app.users.UserType;

import java.util.Scanner;

public class RegisterUserCommand implements Command{
    private final UserDatabase userDatabase;

    public RegisterUserCommand(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        userDatabase.registerUser(name, password, UserType.CUSTOMER);
        System.out.println("Registration successful! You can now log in.");
    }

    public String getName() {
        return "Register";
    }
}
