package tourism_app.menu;

import tourism_app.lib.*;
import tourism_app.menu.commands.*;
import tourism_app.users.User;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Scanner;

public class MainMenu {
    private final DatabaseManager dbManager;
    private final Scanner scanner = new Scanner(System.in);
    private User currentUser;

    public MainMenu(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void start() {
        System.out.println("Welcome to the Tourism App!");

        LinkedHashMap<String, AuthCommand> initialCommands = new LinkedHashMap<>();
        initialCommands.put("1", new RegisterUserCommand(dbManager.getUserDatabase()));
        initialCommands.put("2", new LoginCommand(dbManager.getUserDatabase()));
        initialCommands.put("3", new ExitCommand(dbManager.getUserDatabase()));

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
        LinkedHashMap<String, Command> userCommands = currentUser.getUserType().getMenu(
                dbManager, currentUser);
        Menu userMenu = new Menu(dbManager, currentUser, userCommands);

        while (!userMenu.isLogout()) {
            userMenu.executeSelectedCommand();
        }

        currentUser = null;
        start();
    }
}
