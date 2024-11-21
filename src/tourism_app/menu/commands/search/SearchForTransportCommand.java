package tourism_app.menu.commands.search;

import tourism_app.services.lib.TransportDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.tour.transport.Transport;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForTransportCommand implements Command {
    private final TransportDatabase transportDatabase;
    private final InputValidator inputValidator;

    public SearchForTransportCommand(TransportDatabase transportDatabase, InputValidator inputValidator) {
        this.transportDatabase = transportDatabase;
        this.inputValidator  = inputValidator;
    }

    @Override
    public void execute() {
        transportDatabase.displaySearchFields();

        int choice = inputValidator.getValidIntInRange(1, transportDatabase.getFieldOptionsSize());

        List<Transport> results = transportDatabase.searchTransports(choice);

        if (results.isEmpty()) {
            System.out.println("No transports found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
    return "Search for transport";
}
}
