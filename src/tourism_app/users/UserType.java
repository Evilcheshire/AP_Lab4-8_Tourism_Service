package tourism_app.users;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.menu.MenuItems;
import tourism_app.menu.commands.Command;
import tourism_app.services.utils.InputValidator;
import tourism_app.services.utils.TriFunction;

import java.util.LinkedHashMap;

public enum UserType {
    ADMIN(MenuItems::getAdminManagerMenu,
            MenuItems::getAdminSearchMenu,
            MenuItems::getAdminViewMenu,
            MenuItems::getAdminCreateMenu,
            MenuItems::getAdminDeleteMenu,
            "Admin"),
    MANAGER(MenuItems::getAdminManagerMenu,
            MenuItems::getUserSearchMenu,
            MenuItems::getManagerViewMenu,
            MenuItems::getManagerCreateMenu,
            MenuItems::getManagerDeleteMenu,
            "Manager"),
    CUSTOMER(MenuItems::getCustomerMenu,
            MenuItems::getUserSearchMenu,
            MenuItems::getCustomerViewMenu,
            MenuItems::getCustomerControlMenu,
            MenuItems::getCustomerControlMenu,
            "Customer");

    private final TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> menuSupplier;
    private final TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> searchMenuSupplier;
    private final TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> viewMenuSupplier;
    private final TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> createMenuSupplier;
    private final TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> deleteMenuSupplier;
    public final String NAME;

    UserType(TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> menuSupplier,
             TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> searchMenuSupplier,
             TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> viewMenuSupplier,
             TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> createMenuSupplier,
             TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<String, Command>> deleteMenuSupplier,
             String name) {
        this.menuSupplier = menuSupplier;
        this.searchMenuSupplier = searchMenuSupplier;
        this.viewMenuSupplier = viewMenuSupplier;
        this.createMenuSupplier = createMenuSupplier;
        this.deleteMenuSupplier = deleteMenuSupplier;
        this.NAME = name;
    }

    public LinkedHashMap<String, Command> getMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return menuSupplier.apply(dbManager, user, inputValidator);
    }

    public LinkedHashMap<String, Command> getSearchMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return searchMenuSupplier.apply(dbManager, user, inputValidator);
    }

    public LinkedHashMap<String, Command> getViewMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return viewMenuSupplier.apply(dbManager, user, inputValidator);
    }

    public LinkedHashMap<String, Command> getCreateMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return createMenuSupplier.apply(dbManager, user, inputValidator);
    }

    public LinkedHashMap<String, Command> getDeleteMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return deleteMenuSupplier.apply(dbManager, user, inputValidator);
    }
}
