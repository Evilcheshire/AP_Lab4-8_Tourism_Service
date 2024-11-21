package tourism_app.menu.commands.search;

import tourism_app.services.lib.UserDatabase;
import tourism_app.menu.commands.Command;
import tourism_app.users.User;
import tourism_app.services.utils.InputValidator;

import java.util.List;

public class SearchForUserCommand implements Command {
    private final UserDatabase userDatabase;
    private final InputValidator inputValidator;

    public SearchForUserCommand(UserDatabase userDatabase, InputValidator inputValidator) {
        this.userDatabase = userDatabase;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        userDatabase.displaySearchFields();

        int choice = inputValidator.getValidIntInRange(1, userDatabase.getFieldOptionsSize());

        List<User> results = userDatabase.searchUsers(choice);

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
