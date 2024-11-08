package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;

public class DeleteUserCommand implements Command {
    private final UserDatabase database;

    public DeleteUserCommand(UserDatabase database) {
        this.database = database;
    }

    public void execute() {
        System.out.println("Delete user command has been executed.");
    }

    public String getName(){
        return "Delete user";
    }
}
