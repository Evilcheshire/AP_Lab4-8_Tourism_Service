package tourism_app.control.commands.view;

import tourism_app.services.lib.LocationDatabase;
import tourism_app.control.commands.Command;

public class ViewAllLocationsCommand implements Command {
    private final LocationDatabase locationDB;

    public ViewAllLocationsCommand(LocationDatabase locationDB){
        this.locationDB = locationDB;
    }

    public void execute() {
        System.out.println("Available locations:");
        locationDB.listAllLocations();
    }

    public String getName(){
        return "View all locations";
    }
}
