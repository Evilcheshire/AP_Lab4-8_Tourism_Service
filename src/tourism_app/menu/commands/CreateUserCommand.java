package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;

public class CreateUserCommand implements Command {
    private final UserDatabase database;

    public CreateUserCommand(UserDatabase database) {
        this.database = database;
    }
    public void execute() {
        System.out.println("Create user command has been executed.");
    }

    public String getName(){
        return "Create user";
    }
}
