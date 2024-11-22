package tourism_app.control.commands.deletion;

import tourism_app.services.lib.TransportDatabase;
import tourism_app.control.commands.Command;
import tourism_app.tour.transport.Transport;
import tourism_app.services.utils.InputValidator;

import java.util.Map;

public class DeleteTransportCommand implements Command {
    private final TransportDatabase transportDB;
    private final InputValidator inputValidator;

    public DeleteTransportCommand(TransportDatabase transportDB, InputValidator inputValidator) {
        this.transportDB = transportDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<String, Transport> transports = transportDB.getTransports();

        if (transports.isEmpty()) {
            System.out.println("No transports available to delete.");
            return;
        }

        System.out.println("Available transports to delete:");
        transportDB.listAllTransports();

        System.out.println("Enter the number of the transport to delete:");
        int choice = inputValidator.getValidIntInRange(1, transports.size() + 1);

        String selectedTransportName = (String) transports.keySet().toArray()[choice - 1];
        Transport selectedTransport = transports.get(selectedTransportName);

        if (transportDB.removeTransport(selectedTransport.getName())) {
            System.out.println("Transport \"" + selectedTransport.getName() + "\" has been deleted.");
        } else {
            System.out.println("Failed to delete transport \"" + selectedTransport.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a transport";
    }
}