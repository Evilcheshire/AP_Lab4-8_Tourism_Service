package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;
import tourism_app.users.User;

public class ExitCommand implements Command, AuthCommand {
    private final UserDatabase userDatabase;

    public ExitCommand(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    public User getAuthenticatedUser() { return null; }

    public void execute() {
        userDatabase.saveToFile();
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    public String getName() {
        return "Exit";
    }
}
