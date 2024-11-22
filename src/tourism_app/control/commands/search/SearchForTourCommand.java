package tourism_app.control.commands.search;

import tourism_app.services.lib.TourDatabase;
import tourism_app.services.searchService.TourSearchService;
import tourism_app.control.commands.Command;
import tourism_app.tour.Tour;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForTourCommand implements Command {
    private final TourDatabase tourDatabase;
    private final TourSearchService searchService;
    private final InputValidator inputValidator;

    public SearchForTourCommand(TourDatabase tourDatabase, InputValidator inputValidator) {
        this.tourDatabase = tourDatabase;
        this.searchService = new TourSearchService(inputValidator);
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        searchService.displayAvailableSearchFields();

        int field = inputValidator.getValidIntInRange(1, searchService.getSearchFields().size());

        List<Tour> tours = tourDatabase.getTours();
        List<Tour> results = searchService.search(searchService.getSearchFields().get(field - 1), tours);

        if (results.isEmpty()) {
            System.out.println("No tours found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
        return "Search for tour";
    }
}
