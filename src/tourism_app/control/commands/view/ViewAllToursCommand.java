package tourism_app.control.commands.view;

import tourism_app.services.lib.TourDatabase;
import tourism_app.control.commands.Command;

public class ViewAllToursCommand implements Command {
    private final TourDatabase tourDB;

    public ViewAllToursCommand(TourDatabase tourDB){
        this.tourDB = tourDB;
    }

    public void execute() {
        System.out.println("Available tours:");
        tourDB.listAllTours();
    }
    public String getName(){
        return "View all tours";
    }
}
