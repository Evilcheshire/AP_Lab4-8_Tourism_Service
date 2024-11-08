package tourism_app.menu;

import tourism_app.lib.UserDatabase;
import tourism_app.users.User;

import java.util.Scanner;

public class MainMenu {
    private final UserDatabase userDatabase;
    private final Scanner scanner = new Scanner(System.in);

    public MainMenu(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void start() {
        User currentUser = null;
        while (currentUser == null) {
            System.out.println("Welcome to the Tourism App!");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.print("Choose an option: ");

            String choice = scanner.nextLine();
            currentUser = choice.equals("1") ? register() : login();
        }

        new Menu(currentUser, currentUser.getUserType().getMenu(userDatabase)).executeSelectedCommand();
    }

    private User register() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        User newUser = userDatabase.registerUser(name, password);
        System.out.println("Registration successful! You can now log in.");
        return newUser;
    }

    private User login() {
        System.out.print("Enter username: ");
        String name = scanner.nextLine();
        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        User user = userDatabase.login(name, password);
        if (user != null) {
            System.out.println("Login successful! Welcome, " + name);
        } else {
            System.out.println("Login failed. Please check your username and password.");
        }
        return user;
    }
}
