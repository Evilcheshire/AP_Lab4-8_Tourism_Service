package tourism_app.control.commands.search;

import tourism_app.control.menu.MenuType;
import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.menu.Menu;
import tourism_app.control.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

public class SearchCommand implements Command {
    private final DatabaseManager dbManager;
    private final User user;
    private final InputValidator inputValidator;

    public SearchCommand(DatabaseManager dbManager, User user, InputValidator inputValidator){
        this.dbManager = dbManager;
        this.user = user;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Menu searchMenu = new Menu(
                dbManager,
                user,
                user.getUserType().getMenu(MenuType.SEARCH, dbManager, user, inputValidator),
                inputValidator
        );
        searchMenu.executeSelectedCommand();
    }

    @Override
    public String getName() {
        return "Search for data about tours";
    }
}
