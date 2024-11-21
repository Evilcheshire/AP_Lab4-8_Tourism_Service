package tourism_app.menu;

import tourism_app.services.lib.*;
import tourism_app.menu.commands.Command;
import tourism_app.users.User;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final User user;
    private final DatabaseManager dbManager;
    private final Map<String, Command> commands;
    private boolean isLogout = false;
    private final Scanner scanner;

    public Menu(DatabaseManager dbManager, User user, Map<String, Command> commands, Scanner scanner) {
        this.user = user;
        this.dbManager = dbManager;
        this.commands = commands;
        this.scanner = scanner;
    }

    public void executeSelectedCommand() {
        int attempts = 3;

        while (attempts > 0) {
            System.out.println("Select a command:");

            for (Map.Entry<String, Command> entry : commands.entrySet()) {
                System.out.println(entry.getKey() + " " + entry.getValue().getName());
            }

            String choice = scanner.nextLine().trim();
            Command command = commands.get(choice);

            if (command != null) {
                command.execute();
                if ("Log out".equals(command.getName())) {
                    isLogout = true;
                }
                return;
            } else {
                attempts--;
                System.out.println("Invalid command. Attempts left: " + attempts);
            }
        }

        System.out.println("Too many invalid attempts. Returning to main menu.");
        isLogout = true;
    }


    public boolean isLogout() {
        return isLogout;
    }
}
