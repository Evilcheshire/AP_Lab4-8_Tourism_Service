package tourism_app.services.lib;

import tourism_app.tour.Tour;
import tourism_app.users.UserWithTours;

import java.io.*;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;

public class UserTourDatabase {
    private static final String FILE_PATH = "userWithTours.ser";
    private Map<Integer, UserWithTours> userTours = new LinkedHashMap<>();

    public UserTourDatabase() {
        loadFromFile();
    }

    public void addUserWithTours(UserWithTours userWithTours) {
        userTours.put(userWithTours.getUser().getID(), userWithTours);
    }

    public void listAllUserTours() {
        if (userTours.isEmpty()) {
            System.out.println("No meals available.");
            return;
        }

        int index = 1;
        for (UserWithTours user : userTours.values()) {
            System.out.println(index + ". " + user.toString());
            index++;
        }
    }

    public Tour getTourByName(String tourName) {
        return userTours.values().stream()
                .flatMap(userWithTours -> userWithTours.getSelectedTours().stream())
                .filter(tour -> tour.getName().equalsIgnoreCase(tourName))
                .findFirst()
                .orElse(null);
    }

    public boolean removeToursForUser(int userId) {
        if (userTours.containsKey(userId)) {
            userTours.remove(userId);
            return true;
        }
        return false;
    }

    public void removeTourFromUsers(String tourName) {
        boolean removed = false;
        for (UserWithTours userWithTours : userTours.values()) {
            if (userWithTours.getSelectedTours().removeIf(tour -> tour.getName().equalsIgnoreCase(tourName))) {
                removed = true;
            }
        }

        if (removed) {
            System.out.println("Tour \"" + tourName + "\" removed from all users' selections.");
        } else {
            System.out.println("Tour \"" + tourName + "\" not found in any user's selections.");
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH,false))) {
            out.writeObject(userTours);
        } catch (IOException e) {
            System.out.println("Error saving data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;

        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            userTours = (Map<Integer, UserWithTours>) in.readObject();
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading data: " + e.getMessage());
        }
    }

    public Map<Integer, UserWithTours> getUserTours() {
        return new HashMap<>(userTours);
    }
}
