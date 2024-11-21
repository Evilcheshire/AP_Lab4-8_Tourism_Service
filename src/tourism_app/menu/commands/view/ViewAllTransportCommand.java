package tourism_app.menu.commands.view;

import tourism_app.services.lib.TransportDatabase;
import tourism_app.menu.commands.Command;

public class ViewAllTransportCommand implements Command {
    private final TransportDatabase transportDB;

    public ViewAllTransportCommand(TransportDatabase transportDB){
        this.transportDB = transportDB;
    }

    public void execute() {
        System.out.println("Available transports:");
        transportDB.listAllTransports();
    }
    public String getName(){
        return "View all transport";
    }
}
