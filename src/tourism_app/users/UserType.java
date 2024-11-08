package tourism_app.users;

import tourism_app.lib.UserDatabase;
import tourism_app.menu.MenuItems;
import tourism_app.menu.commands.Command;

import java.util.LinkedHashMap;
import java.util.function.Function;

public enum UserType {
    ADMIN(MenuItems::getAdminMenu),
    MANAGER(MenuItems::getManagerMenu),
    CUSTOMER(MenuItems::getCustomerMenu);

    private final Function<UserDatabase, LinkedHashMap<String, Command>> menuSupplier;

    UserType(Function<UserDatabase, LinkedHashMap<String, Command>> menuSupplier) {
        this.menuSupplier = menuSupplier;
    }

    public LinkedHashMap<String, Command> getMenu(UserDatabase userDatabase) {
        return menuSupplier.apply(userDatabase);
    }
}
