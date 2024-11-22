package tourism_app.control.commands;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.users.User;

public class ExitCommand implements Command{
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
