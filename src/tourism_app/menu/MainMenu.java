package tourism_app.menu;

import tourism_app.services.lib.*;
import tourism_app.menu.commands.*;
import tourism_app.menu.commands.authentification.AuthCommand;
import tourism_app.menu.commands.authentification.LoginCommand;
import tourism_app.menu.commands.authentification.RegisterUserCommand;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.LinkedHashMap;
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
        initialCommands.put("3", new ExitCommand(dbManager));

        while (currentUser == null) {
            System.out.println("Please choose an option:\n1. Register\n2. Log in\n3. Exit");
            String choice = scanner.nextLine().trim();

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
        if (currentUser == null) return;

        InputValidator inputValidator = new InputValidator(scanner);
        LinkedHashMap<String, Command> userCommands = currentUser.getUserType().getMenu(dbManager, currentUser, inputValidator);

        Menu userMenu = new Menu(dbManager, currentUser, userCommands, scanner);

        while (!userMenu.isLogout()) {
            userMenu.executeSelectedCommand();
        }

        currentUser = null;
        start();
    }
}
