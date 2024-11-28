package tourism_app.services.lib;

import tourism_app.tour.meal.Meal;
import tourism_app.users.User;
import tourism_app.users.UserType;

import java.io.*;
import java.util.*;

public class UserDatabase {
    private static final String FILE_PATH = "users.ser";
    private static int nextId = 1;
    private final Map<Integer, User> usersById = new LinkedHashMap<>();
    private final Map<String, User> usersByName = new LinkedHashMap<>();

    public UserDatabase() {
        loadFromFile();
    }

    public User registerUser(String name, String password, UserType userType) {
        if (usersByName.containsKey(name)) {
            return null;
        }

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

    public boolean removeUser(int ID) {
        User user = usersById.remove(ID);
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

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH, false))) {
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

    public void listAllUsers() {
        int index = 1;
        for (User user : usersById.values()) {
            System.out.println(index + ". " + user.toString());
            index++;
        }
    }

    public List<User> getUsersAsList(){
        return  new ArrayList<>(usersById.values());
    }

    public User getUserByName(String name) {
        return usersByName.get(name);
    }

    public User getUserById(int id) {
        return usersById.get(id);
    }

    public Map<Integer, User> getUsersById() {
        return usersById;
    }

}
