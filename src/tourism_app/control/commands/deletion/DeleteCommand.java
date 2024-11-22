package tourism_app.control.commands.deletion;

import tourism_app.control.menu.MenuType;
import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.menu.Menu;
import tourism_app.control.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

public class DeleteCommand implements Command {
    private final DatabaseManager dbManager;
    private final User user;
    private final InputValidator inputValidator;

    public DeleteCommand(DatabaseManager dbManager, User user, InputValidator inputValidator){
        this.dbManager = dbManager;
        this.user = user;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Menu searchMenu = new Menu(
                dbManager,
                user,
                user.getUserType().getMenu(MenuType.DELETE, dbManager, user, inputValidator),
                inputValidator
        );
        searchMenu.executeSelectedCommand();
    }

    @Override
    public String getName() {
        return "Delete data";
    }
}
