package tourism_app.control.commands.authentification;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;
import tourism_app.users.User;

import java.util.Scanner;

public class LoginCommand implements Command{
    private final UserDatabase userDatabase;

    public LoginCommand(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void execute() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter username: ");
        String username = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User authenticatedUser = userDatabase.login(username, password);

        if (authenticatedUser != null) {
            System.out.println("Login successful!");
        } else {
            System.out.println("Invalid username or password.");
        }
    }

    public String getName(){
        return "Log in";
    }
}