package tourism_app.menu;

import tourism_app.lib.*;
import tourism_app.menu.commands.Command;
import tourism_app.menu.commands.LogoutCommand;
import tourism_app.users.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final User user;
    private final DatabaseManager dbManager;
    private final Map<String, Command> commands;
    private boolean isLogout = false;

    public Menu(DatabaseManager dbManager, User user, Map<String, Command> commands) {
        this.user = user;
        this.dbManager = dbManager;
        this.commands = commands;
    }

    public void executeSelectedCommand() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Select a command:");

        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            System.out.println(entry.getKey() + " " + entry.getValue().getName());
        }

        String choice = scanner.nextLine();
        Command command = commands.get(choice);

        if (command != null) {
            command.execute();
            if (command.getName().equals("Log out")) {
                isLogout = true;
            }
        } else {
            System.out.println("Invalid command. Please try again.");
        }
    }

    public boolean isLogout() {
        return isLogout;
    }
}
