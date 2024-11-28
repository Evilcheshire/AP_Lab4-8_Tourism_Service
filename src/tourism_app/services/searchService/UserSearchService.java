package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.users.User;
import tourism_app.users.UserType;

import java.util.function.Predicate;

public class UserSearchService extends SearchService<User> {

    public UserSearchService(InputValidator inputValidator) {
        super(inputValidator);
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("Name", this::searchByName);
        addSearchCriterion("ID", this::searchByID);
        addSearchCriterion("User Type", this::searchByUserType);
    }

    private Predicate<User> searchByName() {
        String name = inputValidator.getValidString("Enter user name: ");
        return user -> user.getName().equalsIgnoreCase(name);
    }

    private Predicate<User> searchByID() {
        int id = inputValidator.getValidIntInRange("Enter user ID: ", 1, Integer.MAX_VALUE);
        return user -> user.getID() == id;
    }

    private Predicate<User> searchByUserType() {
        System.out.println("Available user types:");
        for (UserType type : UserType.values()) {
            System.out.println("- " + type);
        }
        String userTypeInput = inputValidator.getValidString("Select user type: ");
        try {
            UserType userType = UserType.valueOf(userTypeInput.toUpperCase());
            return user -> user.getUserType() == userType;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type. Try again.");
            return searchByUserType();
        }
    }
}

