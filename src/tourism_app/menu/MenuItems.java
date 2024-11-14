package tourism_app.menu;

import tourism_app.lib.DatabaseManager;
import tourism_app.lib.UserDatabase;
import tourism_app.menu.commands.*;
import tourism_app.users.User;

import java.util.LinkedHashMap;

public class MenuItems {

    public static LinkedHashMap<String, Command> getCustomerMenu(DatabaseManager dbManager, User user) {
        LinkedHashMap<String, Command> customerCommands = new LinkedHashMap<>();
        customerCommands.put("1", new SearchForTourCommand());
        customerCommands.put("2", new ViewAllToursCommand());
        customerCommands.put("3", new ViewSelectedToursCommand());
        customerCommands.put("4", new UnselectTourCommand());
        customerCommands.put("5", new LogoutCommand(user, dbManager.getUserDatabase()));
        customerCommands.put("6", new ExitCommand(dbManager.getUserDatabase()));
        return customerCommands;
    }

    public static LinkedHashMap<String, Command> getAdminMenu(DatabaseManager dbManager, User user) {
        LinkedHashMap<String, Command> adminCommands = new LinkedHashMap<>();
        adminCommands.put("1", new SearchForTourCommand());
        adminCommands.put("2", new ViewAllToursCommand());
        adminCommands.put("3", new CreateTourCommand(dbManager.getTourDatabase()));
        adminCommands.put("4", new DeleteTourCommand());
        adminCommands.put("5", new EditTourCommand());
        adminCommands.put("6", new ViewUsersCommand(dbManager.getUserDatabase()));
        adminCommands.put("7", new CreateUserCommand(dbManager.getUserDatabase()));
        adminCommands.put("8", new DeleteUserCommand(dbManager.getUserDatabase()));
        adminCommands.put("9", new LogoutCommand(user, dbManager.getUserDatabase()));
        adminCommands.put("0", new ExitCommand(dbManager.getUserDatabase()));
        return adminCommands;
    }

    public static LinkedHashMap<String, Command> getManagerMenu(DatabaseManager dbManager, User user) {
        LinkedHashMap<String, Command> managerCommands = new LinkedHashMap<>();
        managerCommands.put("1", new SearchForTourCommand());
        managerCommands.put("2", new ViewAllToursCommand());
        managerCommands.put("3", new CreateTourCommand(dbManager.getTourDatabase()));
        managerCommands.put("4", new DeleteTourCommand());
        managerCommands.put("5", new EditTourCommand());
        managerCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        managerCommands.put("0", new ExitCommand(dbManager.getUserDatabase()));
        return managerCommands;
    }
}