package tourism_app.control.commands.deletion;

import tourism_app.services.lib.DatabaseManager;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.tour.transport.Transport;
import tourism_app.services.utils.InputValidator;

import java.util.Map;
import java.util.stream.Collectors;

public class DeleteTransportCommand implements Command {
    private final DatabaseManager databaseManager;
    private final InputValidator inputValidator;

    public DeleteTransportCommand(DatabaseManager databaseManager, InputValidator inputValidator) {
        this.databaseManager = databaseManager;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        Map<String, Transport> transports = databaseManager.getTransportDatabase().getTransports();

        if (transports.isEmpty()) {
            System.out.println("No transports available to delete.");
            return;
        }

        System.out.println("Available transports to delete:");
        databaseManager.getTransportDatabase().listAllTransports();

        int choice = inputValidator.getValidIntInRange("Enter the number of the transport to delete:",1, transports.size());

        String selectedTransportName = (String) transports.keySet().toArray()[choice - 1];
        Transport selectedTransport = transports.get(selectedTransportName);

        if (databaseManager.getTransportDatabase().removeTransport(selectedTransportName)) {
            System.out.println("Transport \"" + selectedTransport.getName() + "\" has been deleted.");

            databaseManager.getTourDatabase().getTours().stream()
                    .filter(tour -> selectedTransport.equals(tour.getTransport()))
                    .collect(Collectors.toList())
                    .forEach(tour -> {
                        databaseManager.getTourDatabase().removeTour(tour.getName(), () ->
                                System.out.println("Tour \"" + tour.getName() + "\" removed due to missing transport."));
                        databaseManager.getUserTourDatabase().removeTourFromUsers(tour.getName());
                    });

            databaseManager.saveAllDatabases();
        } else {
            System.out.println("Failed to delete transport \"" + selectedTransport.getName() + "\".");
        }
    }

    @Override
    public String getName() {
        return "Delete a transport";
    }
}
