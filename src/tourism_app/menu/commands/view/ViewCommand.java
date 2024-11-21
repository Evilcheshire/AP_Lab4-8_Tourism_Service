package tourism_app.menu.commands.view;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.menu.Menu;
import tourism_app.menu.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

public class ViewCommand implements Command {
    private final DatabaseManager dbManager;
    private final User user;
    private final InputValidator inputValidator;

    public ViewCommand(DatabaseManager dbManager, User user, InputValidator inputValidator){
        this.dbManager = dbManager;
        this.user = user;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Menu searchMenu = new Menu(
                dbManager,
                user,
                user.getUserType().getViewMenu(dbManager, user, inputValidator),
                inputValidator.getScanner()
        );
        searchMenu.executeSelectedCommand();
    }

    @Override
    public String getName() {
        return "View information";
    }
}
