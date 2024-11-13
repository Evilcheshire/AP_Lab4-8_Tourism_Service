package tourism_app.menu;

import tourism_app.lib.UserDatabase;
import tourism_app.menu.commands.*;
import tourism_app.users.User;

import java.util.LinkedHashMap;
import java.util.Scanner;

public class MainMenu {
    private final UserDatabase userDatabase;
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public MainMenu(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public void start() {
        System.out.println("Welcome to the Tourism App!");

        LinkedHashMap<String, AuthCommand> initialCommands = new LinkedHashMap<>();
        initialCommands.put("1", new RegisterUserCommand(userDatabase));
        initialCommands.put("2", new LoginCommand(userDatabase));
        initialCommands.put("3", new ExitCommand(userDatabase));

        while (currentUser == null) {
            System.out.println("Please choose an option:\n1. Register\n2. Log in\n3. Exit");
            String choice = scanner.nextLine();

            AuthCommand selectedCommand = initialCommands.get(choice);

            if (selectedCommand != null) {
                selectedCommand.execute();
                currentUser = selectedCommand.getAuthenticatedUser();
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }

        loadUserMenu();
    }

    private void loadUserMenu() {
        LinkedHashMap<String, Command> userCommands = currentUser.getUserType().getMenu(userDatabase, currentUser);
        Menu userMenu = new Menu(userDatabase, currentUser, userCommands);

        while (!userMenu.isLogout()) {
            userMenu.executeSelectedCommand();
        }

        currentUser = null;
        start();
    }
}
