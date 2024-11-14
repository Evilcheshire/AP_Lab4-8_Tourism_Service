package tourism_app.lib;

import tourism_app.users.UserWithTours;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserTourDatabase implements TourismDatabase {
    private static final String FILE_PATH = "userWithTours.ser";
    private Map<Integer, UserWithTours> userTours = new HashMap<>();

    public void addUserWithTours(UserWithTours userWithTours) {
        userTours.put(userWithTours.getUser().getID(), userWithTours);
        saveToFile();
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
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
}