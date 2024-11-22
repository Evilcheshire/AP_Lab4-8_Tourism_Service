package tourism_app.control.commands.search;

import tourism_app.services.lib.UserDatabase;
import tourism_app.services.searchService.UserSearchService;
import tourism_app.control.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.ArrayList;
import java.util.List;

public class SearchForUserCommand implements Command {
    private final UserDatabase userDatabase;
    private final UserSearchService searchService;
    private final InputValidator inputValidator;

    public SearchForUserCommand(UserDatabase userDatabase, InputValidator inputValidator) {
        this.userDatabase = userDatabase;
        this.searchService = new UserSearchService(inputValidator);
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        searchService.displayAvailableSearchFields();

        int field = inputValidator.getValidIntInRange(1, searchService.getSearchFields().size());

        List<User> users = new ArrayList<>(userDatabase.getUsersById().values());
        List<User> results = searchService.search(searchService.getSearchFields().get(field - 1), users);

        if (results.isEmpty()) {
            System.out.println("No users found matching your criteria.");
        } else {
            System.out.println("Search results:");
            results.forEach(System.out::println);
        }
    }

    @Override
    public String getName() {
        return "Search for user";
    }
}
