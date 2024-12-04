package tourism_app.control.commands.view;

import tourism_app.services.lib.UserDatabase;
import tourism_app.control.commands.Command;

public class ViewAllUsersCommand implements Command {
    private final UserDatabase userDB;

    public ViewAllUsersCommand(UserDatabase userDB){
        this.userDB = userDB;
    }

    @Override
    public void execute() {
        System.out.println("Registered users:");
        userDB.listAllItems();
    }

    @Override
    public String getName(){
        return "View users";
    }
}
