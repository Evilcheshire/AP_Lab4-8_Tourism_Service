package tourism_app.menu.commands;

import tourism_app.lib.UserDatabase;

public class RegisterUserCommand implements Command {
    private final UserDatabase database;

    public RegisterUserCommand(UserDatabase database) {
        this.database = database;
    }

    public void execute() {
        System.out.println("Register user command has been executed.");
    }

    public String getName(){
        return "Register user";
    }
}
