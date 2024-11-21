package tourism_app.menu;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.menu.commands.*;
import tourism_app.menu.commands.authentification.LogoutCommand;
import tourism_app.menu.commands.creation.*;
import tourism_app.menu.commands.deletion.*;
import tourism_app.menu.commands.search.*;
import tourism_app.menu.commands.selection.SelectTourCommand;
import tourism_app.menu.commands.selection.UnselectTourCommand;
import tourism_app.menu.commands.view.*;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.LinkedHashMap;

public class MenuItems {

    // Menu for customers
    public static LinkedHashMap<String, Command> getCustomerMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> customerCommands = new LinkedHashMap<>();
        customerCommands.put("1", new SearchCommand(dbManager, user,  inputValidator));
        customerCommands.put("2", new ViewCommand(dbManager, user,  inputValidator));
        customerCommands.put("3", new SelectTourCommand(dbManager.getUserTourDatabase(), user, inputValidator));
        customerCommands.put("4", new UnselectTourCommand(dbManager.getUserTourDatabase(), user, inputValidator));
        customerCommands.put("5", new LogoutCommand(user, dbManager.getUserDatabase()));
        customerCommands.put("0", new ExitCommand(dbManager));
        return customerCommands;
    }

    // Menu for admins
    public static LinkedHashMap<String, Command> getAdminManagerMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> adminCommands = new LinkedHashMap<>();
        adminCommands.put("1", new SearchCommand(dbManager, user,  inputValidator));
        adminCommands.put("2", new ViewCommand(dbManager, user,  inputValidator));
        adminCommands.put("3", new CreateCommand(dbManager, user,  inputValidator));
        adminCommands.put("4", new DeleteCommand(dbManager, user,  inputValidator));
        adminCommands.put("5", new LogoutCommand(user, dbManager.getUserDatabase()));
        adminCommands.put("0", new ExitCommand(dbManager));
        return adminCommands;
    }

    public static LinkedHashMap<String, Command> getAdminSearchMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> searchAdminCommands = new LinkedHashMap<>();
        searchAdminCommands.put("1", new SearchForTourCommand(dbManager.getTourDatabase(), inputValidator));
        searchAdminCommands.put("2", new SearchForLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        searchAdminCommands.put("3", new SearchForTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        searchAdminCommands.put("4", new SearchForMealCommand(dbManager.getMealDatabase(), inputValidator));
        searchAdminCommands.put("5", new SearchForUserCommand(dbManager.getUserDatabase(), inputValidator));
        searchAdminCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        searchAdminCommands.put("7", new BackCommand());
        searchAdminCommands.put("0", new ExitCommand(dbManager));
        return searchAdminCommands;
    }

    public static LinkedHashMap<String, Command> getUserSearchMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> searchUserCommands = new LinkedHashMap<>();
        searchUserCommands.put("1", new SearchForTourCommand(dbManager.getTourDatabase(), inputValidator));
        searchUserCommands.put("2", new SearchForLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        searchUserCommands.put("3", new SearchForTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        searchUserCommands.put("4", new SearchForMealCommand(dbManager.getMealDatabase(), inputValidator));
        searchUserCommands.put("5", new LogoutCommand(user, dbManager.getUserDatabase()));
        searchUserCommands.put("6", new BackCommand());
        searchUserCommands.put("0", new ExitCommand(dbManager));
        return searchUserCommands;
    }

    public static LinkedHashMap<String, Command> getAdminViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> viewAdminCommands = new LinkedHashMap<>();
        viewAdminCommands.put("1", new ViewAllToursCommand(dbManager.getTourDatabase()));
        viewAdminCommands.put("2", new ViewAllLocationsCommand(dbManager.getLocationDatabase()));
        viewAdminCommands.put("3", new ViewAllTransportCommand(dbManager.getTransportDatabase()));
        viewAdminCommands.put("4", new ViewAllMealsCommand(dbManager.getMealDatabase()));
        viewAdminCommands.put("5", new ViewAllUsersCommand(dbManager.getUserDatabase()));
        viewAdminCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        viewAdminCommands.put("7", new BackCommand());
        viewAdminCommands.put("0", new ExitCommand(dbManager));
        return viewAdminCommands;
    }

    public static LinkedHashMap<String, Command> getManagerViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {LinkedHashMap<String, Command> viewAdminCommands = new LinkedHashMap<>();
        LinkedHashMap<String, Command> viewManagerCommands = new LinkedHashMap<>();
        viewManagerCommands.put("1", new ViewAllToursCommand(dbManager.getTourDatabase()));
        viewManagerCommands.put("2", new ViewAllLocationsCommand(dbManager.getLocationDatabase()));
        viewManagerCommands.put("3", new ViewAllTransportCommand(dbManager.getTransportDatabase()));
        viewManagerCommands.put("4", new ViewAllMealsCommand(dbManager.getMealDatabase()));
        viewManagerCommands.put("5", new LogoutCommand(user, dbManager.getUserDatabase()));
        viewManagerCommands.put("6", new BackCommand());
        viewManagerCommands.put("0", new ExitCommand(dbManager));
        return viewManagerCommands;
    }

    public static LinkedHashMap<String, Command> getCustomerViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> viewAdminCommands = new LinkedHashMap<>();
        viewAdminCommands.put("1", new ViewAllToursCommand(dbManager.getTourDatabase()));
        viewAdminCommands.put("2", new ViewSelectedToursCommand(dbManager.getUserTourDatabase()));
        viewAdminCommands.put("3", new ViewAllLocationsCommand(dbManager.getLocationDatabase()));
        viewAdminCommands.put("4", new ViewAllTransportCommand(dbManager.getTransportDatabase()));
        viewAdminCommands.put("5", new ViewAllMealsCommand(dbManager.getMealDatabase()));
        viewAdminCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        viewAdminCommands.put("7", new BackCommand());
        viewAdminCommands.put("0", new ExitCommand(dbManager));
        return viewAdminCommands;
    }

    public static LinkedHashMap<String, Command> getAdminCreateMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> createAdminCommands = new LinkedHashMap<>();
        createAdminCommands.put("1", new CreateTourCommand(dbManager, inputValidator));
        createAdminCommands.put("2", new CreateLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        createAdminCommands.put("3", new CreateTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        createAdminCommands.put("4", new CreateMealCommand(dbManager.getMealDatabase(), inputValidator));
        createAdminCommands.put("5", new CreateUserCommand(dbManager.getUserDatabase(), inputValidator));
        createAdminCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        createAdminCommands.put("7", new BackCommand());
        createAdminCommands.put("0", new ExitCommand(dbManager));
        return createAdminCommands;
    }

    public static LinkedHashMap<String, Command> getManagerCreateMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {LinkedHashMap<String, Command> viewAdminCommands = new LinkedHashMap<>();
        LinkedHashMap<String, Command> createManagerCommands = new LinkedHashMap<>();
        createManagerCommands.put("1", new CreateTourCommand(dbManager, inputValidator));
        createManagerCommands.put("2", new CreateLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        createManagerCommands.put("3", new CreateTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        createManagerCommands.put("4", new CreateMealCommand(dbManager.getMealDatabase(), inputValidator));
        createManagerCommands.put("5", new LogoutCommand(user, dbManager.getUserDatabase()));
        createManagerCommands.put("6", new BackCommand());
        createManagerCommands.put("0", new ExitCommand(dbManager));
        return createManagerCommands;
    }

    public static LinkedHashMap<String, Command> getCustomerControlMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> viewAdminCommands = new LinkedHashMap<>();
        viewAdminCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        viewAdminCommands.put("7", new BackCommand());
        viewAdminCommands.put("0", new ExitCommand(dbManager));
        return viewAdminCommands;
    }

    public static LinkedHashMap<String, Command> getAdminDeleteMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<String, Command> deleteAdminCommands = new LinkedHashMap<>();
        deleteAdminCommands.put("1", new DeleteTourCommand(dbManager.getTourDatabase(), inputValidator));
        deleteAdminCommands.put("2", new DeleteLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        deleteAdminCommands.put("3", new DeleteTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        deleteAdminCommands.put("4", new DeleteMealCommand(dbManager.getMealDatabase(), inputValidator));
        deleteAdminCommands.put("5", new DeleteUserCommand(dbManager.getUserDatabase(), inputValidator));
        deleteAdminCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        deleteAdminCommands.put("7", new BackCommand());
        deleteAdminCommands.put("0", new ExitCommand(dbManager));
        return deleteAdminCommands;
    }

    public static LinkedHashMap<String, Command> getManagerDeleteMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {LinkedHashMap<String, Command> viewAdminCommands = new LinkedHashMap<>();
        LinkedHashMap<String, Command> deleteManagerCommands = new LinkedHashMap<>();
        deleteManagerCommands.put("1", new DeleteTourCommand(dbManager.getTourDatabase(), inputValidator));
        deleteManagerCommands.put("2", new DeleteLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        deleteManagerCommands.put("3", new DeleteTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        deleteManagerCommands.put("4", new DeleteMealCommand(dbManager.getMealDatabase(), inputValidator));
        deleteManagerCommands.put("6", new LogoutCommand(user, dbManager.getUserDatabase()));
        deleteManagerCommands.put("7", new BackCommand());
        deleteManagerCommands.put("0", new ExitCommand(dbManager));
        return deleteManagerCommands;
    }
}
