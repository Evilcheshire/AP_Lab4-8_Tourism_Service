package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;

public class ViewUsersCommand implements Command {
    private final UserDatabase database;

    public ViewUsersCommand(UserDatabase database) {
        this.database = database;
    }

    public void execute() {
        System.out.println("View users command has been executed.");
    }

    public String getName(){
        return "View users";
    }
}
