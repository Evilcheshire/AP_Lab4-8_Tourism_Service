package tourism_app.control.commands.view;

import tourism_app.services.lib.UserTourDatabase;
import tourism_app.control.commands.Command;

public class ViewSelectedToursCommand implements Command {
    private final UserTourDatabase userTourDB;

    public ViewSelectedToursCommand(UserTourDatabase userTourDB){
        this.userTourDB = userTourDB;
    }

    public void execute() {
        System.out.println("Customers and their tours:");
        userTourDB.listAllUserTours();
    }
    public String getName(){
        return "View selected tours";
    }
}
