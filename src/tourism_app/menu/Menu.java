package tourism_app.menu;

import tourism_app.menu.commands.Command;
import tourism_app.menu.commands.ExitCommand;
import tourism_app.users.User;

import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Scanner;

public class Menu {
    private final User user;
    private final LinkedHashMap<Integer, Command> numberedCommands = new LinkedHashMap<>();

    public Menu(User user, LinkedHashMap<String, Command> commands) {
        this.user = user;
        initializeNumberedCommands(commands);
    }

    private void initializeNumberedCommands(LinkedHashMap<String, Command> commands) {
        int number = 1;
        for (Map.Entry<String, Command> entry : commands.entrySet()) {
            numberedCommands.put(number++, entry.getValue());
        }
    }

    public void displayMenu() {
        System.out.println("Welcome, " + user.getUserType() + " " + user.getID());
        System.out.println("Please choose an option:");

        for (Map.Entry<Integer, Command> entry : numberedCommands.entrySet()) {
            System.out.println(entry.getKey() + ". " + entry.getValue().getName());
        }
    }

    public void executeSelectedCommand() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            displayMenu();
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            Command command = numberedCommands.get(choice);
            if (command != null) {
                command.execute();
                if (command instanceof ExitCommand) {
                    break;
                }
            } else {
                System.out.println("Invalid option. Please try again.");
            }
        }
    }
}
