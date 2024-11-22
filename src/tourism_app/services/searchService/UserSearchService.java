package tourism_app.services.searchService;

import tourism_app.services.utils.InputValidator;
import tourism_app.users.User;
import tourism_app.users.UserType;

public class UserSearchService extends SearchService<User> {
    private final InputValidator inputValidator;

    public UserSearchService(InputValidator inputValidator) {
        this.inputValidator = inputValidator;
        initializeSearchCriteria();
    }

    private void initializeSearchCriteria() {
        addSearchCriterion("name", this::searchByName);
        addSearchCriterion("id", this::searchByID);
        addSearchCriterion("userType", this::searchByUserType);
    }

    private boolean searchByName(User user) {
        String name = inputValidator.getValidString("Enter user name to search: ");
        return user.getName().equals(name);
    }

    private boolean searchByID(User user) {
        int id = inputValidator.getValidIntInRange(1, Integer.MAX_VALUE);
        return user.getID() == id;
    }

    private boolean searchByUserType(User user) {
        String userTypeInput = inputValidator.getValidString("Enter user type (Admin, Manager, User): ");
        try {
            UserType userType = UserType.valueOf(userTypeInput.toUpperCase());
            return user.getUserType() == userType;
        } catch (IllegalArgumentException e) {
            System.out.println("Invalid user type. Try again.");
            return searchByUserType(user);
        }
    }
}
