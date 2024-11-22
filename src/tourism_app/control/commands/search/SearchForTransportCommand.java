package tourism_app.control.commands.search;

import tourism_app.services.lib.TransportDatabase;
import tourism_app.services.searchService.TransportSearchService;
import tourism_app.control.commands.Command;
import tourism_app.tour.transport.Transport;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForTransportCommand implements Command {
    private final TransportDatabase transportDatabase;
    private final TransportSearchService searchService;
    private final InputValidator inputValidator;

    public SearchForTransportCommand(TransportDatabase transportDatabase, InputValidator inputValidator) {
        this.transportDatabase = transportDatabase;
        this.searchService = new TransportSearchService(inputValidator);
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        searchService.displayAvailableSearchFields();

        int field = inputValidator.getValidIntInRange(1, searchService.getSearchFields().size());

        List<Transport> results = searchService.search(searchService.getSearchFields().get(field -1 ),
                transportDatabase.getAllTransports());

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
