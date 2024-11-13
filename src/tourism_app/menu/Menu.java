package tourism_app.menu;

import tourism_app.lib.UserDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.menu.commands.LogoutCommand;
import tourism_app.users.User;

import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final User user;
    private final UserDatabase userDatabase;
    private final Map<String, Command> commands;
    private boolean isLogout = false;

    public Menu(UserDatabase userDatabase, User user, Map<String, Command> commands) {
        this.user = user;
        this.userDatabase = userDatabase;
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
