package tourism_app.services.lib;

import tourism_app.users.User;
import tourism_app.users.UserType;

import java.util.*;

public class UserDatabase extends AbstractDatabase<User> {
    private static int nextId = 1;
    private final Map<Integer, User> usersById = new LinkedHashMap<>();

    public UserDatabase() {
        super("users.ser");
        syncIndexes();
    }

    public User registerUser(String name, String password, UserType userType) {
        if (data.containsKey(name)) {
            return null;
        }

        User newUser = new User(nextId++, userType, name, password);
        addItem(name, newUser);
        return newUser;
    }

    public User login(String name, String password) {
        User user = data.get(name);
        if (user != null && user.authenticate(password)) {
            return user;
        }
        return null;
    }

    public boolean removeUserById(int ID, Runnable onSuccess) {
        User user = usersById.get(ID);
        if (user != null) {
            removeItem(user.getName(), onSuccess);
            return true;
        }
        return false;
    }

    public User getUserById(int id) {
        return usersById.get(id);
    }

    public User getUserByName(String name) {
        return data.get(name);
    }

    @Override
    public void addItem(String key, User user) {
        super.addItem(key, user);
        usersById.put(user.getID(), user);
    }

    @Override
    public boolean removeItem(String key, Runnable onSuccess) {
        User user = getItem(key);
        if (user != null) {
            super.removeItem(key, onSuccess);
            usersById.remove(user.getID());
            return true;
        }
        return false;
    }

    public Map<Integer, User> getUsersById() {
        return usersById;
    }

    @Override
    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        super.loadFromFile();
        syncIndexes();
    }

    private void syncIndexes() {

        if (data != null && !data.isEmpty()) {
            for (User user : data.values()) {
                if (user != null) {
                    usersById.put(user.getID(), user);
                }
            }
            nextId = usersById.keySet().stream().max(Integer::compare).orElse(0) + 1;
        } else {
            nextId = 1;
        }
    }

}
