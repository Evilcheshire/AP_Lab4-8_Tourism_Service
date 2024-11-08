package tourism_app.menu;

import tourism_app.lib.UserDatabase;
import tourism_app.menu.commands.*;
import java.util.LinkedHashMap;

public class MenuItems {

    public static LinkedHashMap<String, Command> getCustomerMenu(UserDatabase userDatabase) {
        LinkedHashMap<String, Command> customerCommands = new LinkedHashMap<>();
        customerCommands.put("1", new SearchForTourCommand());
        customerCommands.put("2", new ViewAllToursCommand());
        customerCommands.put("3", new ViewSelectedToursCommand());
        customerCommands.put("4", new UnselectTourCommand());
        customerCommands.put("5", new LogoutCommand(userDatabase));
        customerCommands.put("6", new ExitCommand());
        return customerCommands;
    }

    public static LinkedHashMap<String, Command> getAdminMenu(UserDatabase userDatabase) {
        LinkedHashMap<String, Command> adminCommands = new LinkedHashMap<>();
        adminCommands.put("1", new SearchForTourCommand());
        adminCommands.put("2", new ViewAllToursCommand());
        adminCommands.put("3", new CreateTourCommand());
        adminCommands.put("4", new DeleteTourCommand());
        adminCommands.put("5", new EditTourCommand());
        adminCommands.put("6", new ViewUsersCommand(userDatabase));
        adminCommands.put("7", new CreateUserCommand(userDatabase));
        adminCommands.put("8", new DeleteUserCommand(userDatabase));
        adminCommands.put("9", new LogoutCommand(userDatabase));
        adminCommands.put("0", new ExitCommand());
        return adminCommands;
    }

    public static LinkedHashMap<String, Command> getManagerMenu(UserDatabase userDatabase) {
        LinkedHashMap<String, Command> managerCommands = new LinkedHashMap<>();
        managerCommands.put("1", new SearchForTourCommand());
        managerCommands.put("2", new ViewAllToursCommand());
        managerCommands.put("3", new CreateTourCommand());
        managerCommands.put("4", new DeleteTourCommand());
        managerCommands.put("5", new EditTourCommand());
        managerCommands.put("6", new LogoutCommand(userDatabase));
        managerCommands.put("0", new ExitCommand());
        return managerCommands;
    }
}
