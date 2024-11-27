package tourism_app.control.menu;

import tourism_app.services.lib.*;
import tourism_app.control.commands.*;
import tourism_app.control.commands.authentification.LoginCommand;
import tourism_app.control.commands.authentification.RegisterUserCommand;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class MainMenu {
    private final DatabaseManager dbManager;
    private final InputValidator inputValidator = new InputValidator(new Scanner(System.in));
    private User currentUser;

    public MainMenu(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public void start() {
        System.out.println("Welcome to the Tourism App!");

        Map<Integer, Command> initialCommands = new LinkedHashMap<>();
        initialCommands.put(1, new RegisterUserCommand(dbManager.getUserDatabase(), inputValidator));
        initialCommands.put(2, new LoginCommand(dbManager.getUserDatabase(), inputValidator));
        initialCommands.put(3, new ExitCommand(dbManager));

        while (currentUser == null) {
            System.out.println("Please choose an option:");
            initialCommands.forEach((key, command) -> System.out.println(key + ". " + command.getName()));

            int choice = inputValidator.getValidIntInRange(1, initialCommands.size());
            Command selectedCommand = initialCommands.get(choice);

            if (selectedCommand != null) {
                selectedCommand.execute();
                currentUser = selectedCommand.getAuthUser();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        loadUserMenu();
    }

    private void loadUserMenu() {
        if (currentUser == null) return;
        Map<Integer, Command> userCommands = currentUser.getUserType().getMenu(dbManager, currentUser, inputValidator);

        Menu userMenu = new Menu(dbManager, currentUser, userCommands, inputValidator);

        while (!userMenu.isLogout()) {
            userMenu.executeSelectedCommand();
        }

        currentUser = null;
        start();
    }
}
