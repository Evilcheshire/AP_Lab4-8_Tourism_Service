package tourism_app.control.commands.authentification;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;

public class LogoutCommand implements Command {
    private final UserDatabase userDatabase;

    public LogoutCommand(UserDatabase userDatabase) {
        this.userDatabase = userDatabase;
    }

    @Override
    public void execute() {
        System.out.println("Logging out...");
        userDatabase.saveToFile();
    }

    @Override
    public String getName() {
        return "Log out";
    }
}

