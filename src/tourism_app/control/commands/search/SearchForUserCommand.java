package tourism_app.control.commands.search;

import tourism_app.services.lib.UserDatabase;
import tourism_app.services.searchService.UserSearchService;
import tourism_app.control.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForUserCommand implements Command {
    private final UserDatabase userDatabase;
    private final UserSearchService searchService;

    public SearchForUserCommand(UserDatabase userDatabase, InputValidator inputValidator) {
        this.userDatabase = userDatabase;
        this.searchService = new UserSearchService(inputValidator);
    }

    @Override
    public void execute() {
        List<User> users = userDatabase.getUsersAsList();
        List<User> results = searchService.search(users);

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
