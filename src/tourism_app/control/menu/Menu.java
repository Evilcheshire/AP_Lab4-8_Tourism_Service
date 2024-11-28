package tourism_app.control.menu;

import tourism_app.control.commands.authentification.LogoutCommand;
import tourism_app.services.lib.*;
import tourism_app.control.commands.Command;
import tourism_app.services.utils.InputValidator;
import tourism_app.users.User;

import java.util.Map;

public class Menu {
    private final Map<Integer, Command> commands;
    private boolean isLogout = false;
    private final InputValidator inputValidator;

    public Menu(DatabaseManager dbManager, User user, Map<Integer, Command> commands, InputValidator inputValidator) {
        this.commands = commands;
        this.inputValidator = inputValidator;
    }

    public void executeSelectedCommand() {
        commands.forEach((key, command) -> System.out.println(key + ". " + command.getName()));

        int choice = inputValidator.getValidIntInRange("Select a command:",0, commands.size() - 1);
        Command command = commands.get(choice);

        if (command != null) {
            command.execute();
            if (command instanceof LogoutCommand) {
                isLogout = true;
            }
        } else {
            System.out.println("Invalid choice. Please try again.");
        }
    }

    public boolean isLogout() {
        return isLogout;
    }
}
