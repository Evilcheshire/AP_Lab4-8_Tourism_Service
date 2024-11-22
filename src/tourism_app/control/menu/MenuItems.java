package tourism_app.control.menu;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.*;
import tourism_app.control.commands.authentification.LogoutCommand;
import tourism_app.control.commands.creation.*;
import tourism_app.control.commands.deletion.*;
import tourism_app.control.commands.search.*;
import tourism_app.control.commands.selection.SelectTourCommand;
import tourism_app.control.commands.selection.UnselectTourCommand;
import tourism_app.control.commands.view.*;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.LinkedHashMap;

public class MenuItems {

    public static LinkedHashMap<Integer, Command> getCustomerMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> customerCommands = new LinkedHashMap<>();
        customerCommands.put(1, new SearchCommand(dbManager, user, inputValidator));
        customerCommands.put(2, new ViewCommand(dbManager, user, inputValidator));
        customerCommands.put(3, new SelectTourCommand(dbManager, user, inputValidator));
        customerCommands.put(4, new UnselectTourCommand(dbManager.getUserTourDatabase(), user, inputValidator));
        customerCommands.put(5, new LogoutCommand(dbManager.getUserDatabase()));
        customerCommands.put(0, new ExitCommand(dbManager));
        return customerCommands;
    }

    public static LinkedHashMap<Integer, Command> getAdminManagerMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> adminCommands = new LinkedHashMap<>();
        adminCommands.put(1, new SearchCommand(dbManager, user, inputValidator));
        adminCommands.put(2, new ViewCommand(dbManager, user, inputValidator));
        adminCommands.put(3, new CreateCommand(dbManager, user, inputValidator));
        adminCommands.put(4, new DeleteCommand(dbManager, user, inputValidator));
        adminCommands.put(5, new LogoutCommand(dbManager.getUserDatabase()));
        adminCommands.put(0, new ExitCommand(dbManager));
        return adminCommands;
    }

    public static LinkedHashMap<Integer, Command> getAdminSearchMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> searchAdminCommands = new LinkedHashMap<>();
        searchAdminCommands.put(1, new SearchForTourCommand(dbManager.getTourDatabase(), inputValidator));
        searchAdminCommands.put(2, new SearchForLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        searchAdminCommands.put(3, new SearchForTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        searchAdminCommands.put(4, new SearchForMealCommand(dbManager.getMealDatabase(), inputValidator));
        searchAdminCommands.put(5, new SearchForUserCommand(dbManager.getUserDatabase(), inputValidator));
        searchAdminCommands.put(6, new LogoutCommand(dbManager.getUserDatabase()));
        searchAdminCommands.put(7, new BackCommand());
        searchAdminCommands.put(0, new ExitCommand(dbManager));
        return searchAdminCommands;
    }

    public static LinkedHashMap<Integer, Command> getUserSearchMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> searchUserCommands = new LinkedHashMap<>();
        searchUserCommands.put(1, new SearchForTourCommand(dbManager.getTourDatabase(), inputValidator));
        searchUserCommands.put(2, new SearchForLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        searchUserCommands.put(3, new SearchForTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        searchUserCommands.put(4, new SearchForMealCommand(dbManager.getMealDatabase(), inputValidator));
        searchUserCommands.put(5, new LogoutCommand(dbManager.getUserDatabase()));
        searchUserCommands.put(6, new BackCommand());
        searchUserCommands.put(0, new ExitCommand(dbManager));
        return searchUserCommands;
    }

    public static LinkedHashMap<Integer, Command> getAdminViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> viewAdminCommands = new LinkedHashMap<>();
        viewAdminCommands.put(1, new ViewAllToursCommand(dbManager.getTourDatabase()));
        viewAdminCommands.put(2, new ViewAllLocationsCommand(dbManager.getLocationDatabase()));
        viewAdminCommands.put(3, new ViewAllTransportCommand(dbManager.getTransportDatabase()));
        viewAdminCommands.put(4, new ViewAllMealsCommand(dbManager.getMealDatabase()));
        viewAdminCommands.put(5, new ViewAllUsersCommand(dbManager.getUserDatabase()));
        viewAdminCommands.put(6, new LogoutCommand(dbManager.getUserDatabase()));
        viewAdminCommands.put(7, new BackCommand());
        viewAdminCommands.put(0, new ExitCommand(dbManager));
        return viewAdminCommands;
    }

    public static LinkedHashMap<Integer, Command> getManagerViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> viewManagerCommands = new LinkedHashMap<>();
        viewManagerCommands.put(1, new ViewAllToursCommand(dbManager.getTourDatabase()));
        viewManagerCommands.put(2, new ViewAllLocationsCommand(dbManager.getLocationDatabase()));
        viewManagerCommands.put(3, new ViewAllTransportCommand(dbManager.getTransportDatabase()));
        viewManagerCommands.put(4, new ViewAllMealsCommand(dbManager.getMealDatabase()));
        viewManagerCommands.put(5, new LogoutCommand(dbManager.getUserDatabase()));
        viewManagerCommands.put(6, new BackCommand());
        viewManagerCommands.put(0, new ExitCommand(dbManager));
        return viewManagerCommands;
    }

    public static LinkedHashMap<Integer, Command> getCustomerViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> viewCustomerCommands = new LinkedHashMap<>();
        viewCustomerCommands.put(1, new ViewAllToursCommand(dbManager.getTourDatabase()));
        viewCustomerCommands.put(2, new ViewSelectedToursCommand(dbManager.getUserTourDatabase()));
        viewCustomerCommands.put(3, new ViewAllLocationsCommand(dbManager.getLocationDatabase()));
        viewCustomerCommands.put(4, new ViewAllTransportCommand(dbManager.getTransportDatabase()));
        viewCustomerCommands.put(5, new ViewAllMealsCommand(dbManager.getMealDatabase()));
        viewCustomerCommands.put(6, new LogoutCommand(dbManager.getUserDatabase()));
        viewCustomerCommands.put(7, new BackCommand());
        viewCustomerCommands.put(0, new ExitCommand(dbManager));
        return viewCustomerCommands;
    }

    public static LinkedHashMap<Integer, Command> getAdminCreateMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> createAdminCommands = new LinkedHashMap<>();
        createAdminCommands.put(1, new CreateTourCommand(dbManager, inputValidator));
        createAdminCommands.put(2, new CreateLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        createAdminCommands.put(3, new CreateTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        createAdminCommands.put(4, new CreateMealCommand(dbManager.getMealDatabase(), inputValidator));
        createAdminCommands.put(5, new CreateUserCommand(dbManager.getUserDatabase(), inputValidator));
        createAdminCommands.put(6, new LogoutCommand(dbManager.getUserDatabase()));
        createAdminCommands.put(7, new BackCommand());
        createAdminCommands.put(0, new ExitCommand(dbManager));
        return createAdminCommands;
    }

    public static LinkedHashMap<Integer, Command> getManagerCreateMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> createManagerCommands = new LinkedHashMap<>();
        createManagerCommands.put(1, new CreateTourCommand(dbManager, inputValidator));
        createManagerCommands.put(2, new CreateLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        createManagerCommands.put(3, new CreateTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        createManagerCommands.put(4, new CreateMealCommand(dbManager.getMealDatabase(), inputValidator));
        createManagerCommands.put(5, new LogoutCommand(dbManager.getUserDatabase()));
        createManagerCommands.put(6, new BackCommand());
        createManagerCommands.put(0, new ExitCommand(dbManager));
        return createManagerCommands;
    }

    public static LinkedHashMap<Integer, Command> getAdminDeleteMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> deleteAdminCommands = new LinkedHashMap<>();
        deleteAdminCommands.put(1, new DeleteTourCommand(dbManager, inputValidator));
        deleteAdminCommands.put(2, new DeleteLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        deleteAdminCommands.put(3, new DeleteTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        deleteAdminCommands.put(4, new DeleteMealCommand(dbManager.getMealDatabase(), inputValidator));
        deleteAdminCommands.put(5, new DeleteUserCommand(dbManager, inputValidator));
        deleteAdminCommands.put(6, new LogoutCommand(dbManager.getUserDatabase()));
        deleteAdminCommands.put(7, new BackCommand());
        deleteAdminCommands.put(0, new ExitCommand(dbManager));
        return deleteAdminCommands;
    }

    public static LinkedHashMap<Integer, Command> getManagerDeleteMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        LinkedHashMap<Integer, Command> deleteManagerCommands = new LinkedHashMap<>();
        deleteManagerCommands.put(1, new DeleteTourCommand(dbManager, inputValidator));
        deleteManagerCommands.put(2, new DeleteLocationCommand(dbManager.getLocationDatabase(), inputValidator));
        deleteManagerCommands.put(3, new DeleteTransportCommand(dbManager.getTransportDatabase(), inputValidator));
        deleteManagerCommands.put(4, new DeleteMealCommand(dbManager.getMealDatabase(), inputValidator));
        deleteManagerCommands.put(5, new LogoutCommand(dbManager.getUserDatabase()));
        deleteManagerCommands.put(6, new BackCommand());
        deleteManagerCommands.put(0, new ExitCommand(dbManager));
        return deleteManagerCommands;
    }
}
