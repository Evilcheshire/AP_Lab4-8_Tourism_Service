package tourism_app.menu.commands.authentification;

import tourism_app.services.lib.UserDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.users.User;

public class LogoutCommand implements Command {
    private final User user;
    private final UserDatabase userDatabase;

    public LogoutCommand(User user, UserDatabase userDatabase) {
        this.user = user;
        this.userDatabase = userDatabase;
    }

    public void execute() {
        System.out.println("Logging out...");
        userDatabase.saveToFile();
    }

    public String getName() {
        return "Log out";
    }
}

