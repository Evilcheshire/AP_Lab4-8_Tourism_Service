package tourism_app.services.lib;

import tourism_app.users.User;
import tourism_app.users.UserType;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class UserDatabase {
    private static final String FILE_PATH = "users.ser";
    private static int nextId = 1;
    private final Map<Integer, User> usersById = new LinkedHashMap<>();
    private final Map<String, User> usersByName = new LinkedHashMap<>();

    private final Map<Integer, String> fieldOptions = Map.of(
            1, "name",
            2, "userType",
            3, "ID"
    );

    private final Map<String, Predicate<User>> searchCriteria;

    public UserDatabase() {
        loadFromFile();
        searchCriteria = initializeSearchCriteria();
    }

    private Map<String, Predicate<User>> initializeSearchCriteria() {
        Map<String, Predicate<User>> criteria = new HashMap<>();

        // Пошук за іменем
        criteria.put("name", user -> {
            String name = getUserInput("Enter user name to search: ");
            return user.getName().equalsIgnoreCase(name);
        });

        // Пошук за типом користувача
        criteria.put("userType", user -> {
            String type = getUserInput("Enter user type (e.g., ADMIN, CLIENT, GUIDE): ");
            try {
                return user.getUserType().toString().equalsIgnoreCase(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type entered.");
                return false;
            }
        });

        // Пошук за ID
        criteria.put("ID", user -> {
            String idInput = getUserInput("Enter user ID to search: ");
            try {
                int id = Integer.parseInt(idInput);
                return user.getID() == id;
            } catch (NumberFormatException e) {
                System.out.println("Invalid ID entered.");
                return false;
            }
        });

        return criteria;
    }

    private String getUserInput(String prompt) {
        System.out.print(prompt);
        Scanner scanner = new Scanner(System.in);
        return scanner.nextLine();
    }

    public void displaySearchFields() {
        System.out.println("Choose a field to search by:");
        fieldOptions.forEach((key, value) -> System.out.println(key + " - " + value));
    }

    public List<User> searchUsers(int option) {
        String field = fieldOptions.get(option);
        if (field == null) {
            System.out.println("Invalid option selected.");
            return Collections.emptyList();
        }

        Predicate<User> criteria = searchCriteria.get(field);
        List<User> result = usersById.values().stream()
                .filter(criteria)
                .collect(Collectors.toList());

        // Відображення результатів
        if (result.isEmpty()) {
            System.out.println("No users found matching the criteria.");
        } else {
            result.forEach(user -> System.out.println("ID: " + user.getID() + ", Name: " + user.getName() + ", Type: " + user.getUserType()));
        }
        return result;
    }

    public User registerUser(String name, String password, UserType userType) {
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

    public void listAllUsers() {
        usersById.values().forEach(user -> System.out.println("ID: " + user.getID() + ", Name: " + user.getName() + ", Type: " + user.getUserType()));
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

    public int getFieldOptionsSize() {
        return fieldOptions.size();
    }
}
