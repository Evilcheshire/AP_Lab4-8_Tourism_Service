package tourism_app.users;

import tourism_app.control.menu.MenuType;
import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.menu.MenuItems;
import tourism_app.control.commands.Command;
import tourism_app.services.utils.InputValidator;
import tourism_app.services.utils.TriFunction;

import java.util.LinkedHashMap;
import java.util.EnumMap;
import java.util.Map;

public enum UserType {
    ADMIN("Admin",
            MenuItems::getAdminManagerMenu,
            Map.of(
                    MenuType.SEARCH, MenuItems::getAdminSearchMenu,
                    MenuType.VIEW, MenuItems::getAdminViewMenu,
                    MenuType.CREATE, MenuItems::getAdminCreateMenu,
                    MenuType.DELETE, MenuItems::getAdminDeleteMenu
            )
    ),
    MANAGER("Manager",
            MenuItems::getAdminManagerMenu,
            Map.of(
                    MenuType.SEARCH, MenuItems::getUserSearchMenu,
                    MenuType.VIEW, MenuItems::getManagerViewMenu,
                    MenuType.CREATE, MenuItems::getManagerCreateMenu,
                    MenuType.DELETE, MenuItems::getManagerDeleteMenu
            )
    ),
    CUSTOMER("Customer",
            MenuItems::getCustomerMenu,
            Map.of(
                    MenuType.SEARCH, MenuItems::getUserSearchMenu,
                    MenuType.VIEW, MenuItems::getCustomerViewMenu
            )
    );

    private final String name;
    private final TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<Integer, Command>> mainMenuSupplier;
    private final Map<MenuType, TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<Integer, Command>>> menuSuppliers;

    UserType(String name,
             TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<Integer, Command>> mainMenuSupplier,
             Map<MenuType, TriFunction<DatabaseManager, User, InputValidator, LinkedHashMap<Integer, Command>>> menuSuppliers) {
        this.name = name;
        this.mainMenuSupplier = mainMenuSupplier;
        this.menuSuppliers = new EnumMap<>(menuSuppliers);
    }

    public String getName() {
        return name;
    }

    public LinkedHashMap<Integer, Command> getMenu(DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return mainMenuSupplier.apply(dbManager, user, inputValidator);
    }

    public LinkedHashMap<Integer, Command> getMenu(MenuType type, DatabaseManager dbManager, User user, InputValidator inputValidator) {
        return menuSuppliers.getOrDefault(type, (dm, u, iv) -> new LinkedHashMap<>()).apply(dbManager, user, inputValidator);
    }
}
