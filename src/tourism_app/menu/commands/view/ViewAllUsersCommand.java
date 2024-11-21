package tourism_app.menu.commands.view;

import tourism_app.services.lib.UserDatabase;
import tourism_app.menu.commands.Command;

public class ViewAllUsersCommand implements Command {
    private final UserDatabase userDB;

    public ViewAllUsersCommand(UserDatabase userDB){
        this.userDB = userDB;
    }

    public void execute() {
        System.out.println("Registered users:");
        userDB.listAllUsers();
    }
    public String getName(){
        return "View users";
    }
}
