package tourism_app.services.lib;

import tourism_app.tour.Tour;
import tourism_app.users.UserWithTours;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class UserTourDatabase extends AbstractDatabase<UserWithTours> {
    private final Map<Integer, UserWithTours> userToursById = new LinkedHashMap<>();

    public UserTourDatabase() {
        super("userWithTours.ser");
        syncIndexes();
    }

    public void addUserWithTours(UserWithTours userWithTours) {
        addItem(String.valueOf(userWithTours.getUser().getID()), userWithTours);
    }

    public boolean removeToursForUser(int userId, Runnable onSuccess) {
        UserWithTours userWithTours = userToursById.remove(userId);
        if (userWithTours != null) {
            removeItem(String.valueOf(userId), onSuccess);
            return true;
        }
        return false;
    }

    public void removeTourFromUsers(String tourName) {
        boolean removed = false;
        for (UserWithTours userWithTours : userToursById.values()) {
            if (userWithTours.getSelectedTours().removeIf(tour -> tour.getName().equalsIgnoreCase(tourName))) {
                removed = true;
            }
        }

        if (removed) {
            System.out.println("Tour \"" + tourName + "\" removed from all users' selections.");
        } else {
            System.out.println("Tour \"" + tourName + "\" not found in any user's selections.");
        }
        saveToFile();
    }

    @Override
    public void addItem(String key, UserWithTours userWithTours) {
        super.addItem(key, userWithTours);
        userToursById.put(userWithTours.getUser().getID(), userWithTours);
    }

    public boolean removeItem(String key, Runnable onSuccess) {
        UserWithTours userWithTours = getItem(key);
        if (userWithTours != null) {
            super.removeItem(key, onSuccess);
            userToursById.remove(userWithTours.getUser().getID());
            return true;
        }
        return false;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        super.loadFromFile();
        syncIndexes();
    }

    private void syncIndexes() {
        for (UserWithTours userWithTours : data.values()) {
            userToursById.put(userWithTours.getUser().getID(), userWithTours);
        }
    }

    public void listAllUserTours() {
        if (data.isEmpty()) {
            System.out.println("No user tours available.");
        } else {
            int index = 1;
            for (UserWithTours userWithTours : data.values()) {
                System.out.println(index + ". " + userWithTours);
                index++;
            }
        }
    }

    public Map<Integer, UserWithTours> getUserToursById() {
        return new LinkedHashMap<>(userToursById);
    }
}
