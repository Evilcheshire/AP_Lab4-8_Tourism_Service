package tourism_app.control.commands.view;

import tourism_app.services.lib.TransportDatabase;
import tourism_app.control.commands.Command;

public class ViewAllTransportCommand implements Command {
    private final TransportDatabase transportDB;

    public ViewAllTransportCommand(TransportDatabase transportDB){
        this.transportDB = transportDB;
    }

    @Override
    public void execute() {
        System.out.println("Available transports:");
        transportDB.listAllItems();
    }

    @Override
    public String getName(){
        return "View all transport";
    }
}
