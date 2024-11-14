package tourism_app.lib;

import tourism_app.users.User;
import tourism_app.users.UserType;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class UserDatabase implements TourismDatabase {
    private static final String FILE_PATH = "users.ser";
    private static int nextId = 1;
    private final Map<Integer, User> usersById = new HashMap<>();
    private final Map<String, User> usersByName = new HashMap<>();

    public UserDatabase() {
        loadFromFile();
    }

    public User registerUser(String name, String password) {
        User newUser = new User(nextId++, UserType.CUSTOMER, name, password);
        usersById.put(newUser.getID(), newUser);
        usersByName.put(name, newUser);
        saveToFile();
        return newUser;
    }

    public User addAdminOrManager(String name, String password, UserType userType) {
        if (userType == UserType.CUSTOMER)
            throw new IllegalArgumentException("Cannot create CUSTOMER with this method");
        User newUser = new User(nextId++, userType, name, password);
        usersById.put(newUser.getID(), newUser);
        usersByName.put(name, newUser);
        saveToFile();
        return newUser;
    }

    public User login(String name, String password) {
        User user = usersByName.get(name);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(usersById);
        } catch (IOException e) {
            System.out.println("Error saving user data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            usersById.putAll((Map<Integer, User>) in.readObject());
            nextId = usersById.keySet().stream().max(Integer::compare).orElse(0) + 1;
            usersById.values().forEach(user -> usersByName.put(user.getName(), user));
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading user data: " + e.getMessage());
        }
    }

    public boolean removeUser(String name) {
        User user = usersByName.remove(name);
        if (user != null) {
            usersById.remove(user.getID());
            saveToFile();
            return true;
        }
        return false;
    }

    public boolean updatePassword(String name, String newPassword) {
        User user = usersByName.get(name);
        if (user != null) {
            user.setPassword(newPassword);
            saveToFile();
            return true;
        }
        return false;
    }

    public void listAllUsers() {
        usersById.values().forEach(user -> System.out.println("ID: " + user.getID() + ", Name: " + user.getName() + ", Type: " + user.getUserType()));
    }
}