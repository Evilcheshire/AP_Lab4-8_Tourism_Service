package tourism_app.menu.commands;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.menu.commands.authentification.AuthCommand;
import tourism_app.users.User;

public class ExitCommand implements Command, AuthCommand {
    private final DatabaseManager dbManager;

    public ExitCommand(DatabaseManager dbManager) {
        this.dbManager = dbManager;
    }

    public User getAuthenticatedUser() { return null; }

    public void execute() {
        dbManager.saveAllDatabases();
        System.out.println("Exiting the application. Goodbye!");
        System.exit(0);
    }

    public String getName() {
        return "Exit";
    }
}
