package tourism_app.menu;

import tourism_app.lib.UserDatabase;
import tourism_app.menu.commands.*;
import tourism_app.users.User;

import java.util.LinkedHashMap;

public class MenuItems {

    public static LinkedHashMap<String, Command> getCustomerMenu(UserDatabase userDatabase, User user) {
        LinkedHashMap<String, Command> customerCommands = new LinkedHashMap<>();
        customerCommands.put("1", new SearchForTourCommand());
        customerCommands.put("2", new ViewAllToursCommand());
        customerCommands.put("3", new ViewSelectedToursCommand());
        customerCommands.put("4", new UnselectTourCommand());
        customerCommands.put("5", new LogoutCommand(user, userDatabase));
        customerCommands.put("6", new ExitCommand(userDatabase));
        return customerCommands;
    }

    public static LinkedHashMap<String, Command> getAdminMenu(UserDatabase userDatabase, User user) {
        LinkedHashMap<String, Command> adminCommands = new LinkedHashMap<>();
        adminCommands.put("1", new SearchForTourCommand());
        adminCommands.put("2", new ViewAllToursCommand());
        adminCommands.put("3", new CreateTourCommand());
        adminCommands.put("4", new DeleteTourCommand());
        adminCommands.put("5", new EditTourCommand());
        adminCommands.put("6", new ViewUsersCommand(userDatabase));
        adminCommands.put("7", new CreateUserCommand(userDatabase));
        adminCommands.put("8", new DeleteUserCommand(userDatabase));
        adminCommands.put("9", new LogoutCommand(user, userDatabase));
        adminCommands.put("0", new ExitCommand(userDatabase));
        return adminCommands;
    }

    public static LinkedHashMap<String, Command> getManagerMenu(UserDatabase userDatabase, User user) {
        LinkedHashMap<String, Command> managerCommands = new LinkedHashMap<>();
        managerCommands.put("1", new SearchForTourCommand());
        managerCommands.put("2", new ViewAllToursCommand());
        managerCommands.put("3", new CreateTourCommand());
        managerCommands.put("4", new DeleteTourCommand());
        managerCommands.put("5", new EditTourCommand());
        managerCommands.put("6", new LogoutCommand(user, userDatabase));
        managerCommands.put("0", new ExitCommand(userDatabase));
        return managerCommands;
    }
}