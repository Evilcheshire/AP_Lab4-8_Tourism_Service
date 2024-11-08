package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;

public class LogoutCommand implements Command {
    private final UserDatabase database;

    public LogoutCommand(UserDatabase database) {
        this.database = database;
    }
    public void execute() {
        System.out.println("Logout command has been executed.");
    }

    public String getName(){
        return "Log out";
    }
}
