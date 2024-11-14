package tourism_app.users;

import tourism_app.lib.DatabaseManager;
import tourism_app.lib.UserDatabase;
import tourism_app.menu.MenuItems;
import tourism_app.menu.commands.Command;

import java.util.LinkedHashMap;
import java.util.function.BiFunction;

public enum UserType {
    ADMIN(MenuItems::getAdminMenu),
    MANAGER(MenuItems::getManagerMenu),
    CUSTOMER(MenuItems::getCustomerMenu);

    private final BiFunction<DatabaseManager, User, LinkedHashMap<String, Command>> menuSupplier;

    UserType(BiFunction<DatabaseManager, User, LinkedHashMap<String, Command>> menuSupplier) {
        this.menuSupplier = menuSupplier;
    }

    public LinkedHashMap<String, Command> getMenu(DatabaseManager dbManager, User user) {
        return menuSupplier.apply(dbManager, user);
    }
}
