package tourism_app.services.lib;

import tourism_app.tour.location.Location;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class LocationDatabase {
    private static final String FILE_PATH = "locations.ser";
    private final Map<String, Location> locations = new LinkedHashMap<>();

    private final Map<Integer, String> fieldOptions = Map.of(
            1, "name",
            2, "country",
            3, "type"
    );

    private final Map<String, Predicate<Location>> searchCriteria;

    public LocationDatabase() {
        loadFromFile();
        searchCriteria = initializeSearchCriteria();
    }

    private Map<String, Predicate<Location>> initializeSearchCriteria() {
        Map<String, Predicate<Location>> criteria = new HashMap<>();

        criteria.put("name", location -> {
            String name = getUserInput("Enter name to search: ");
            return location.getName().equalsIgnoreCase(name);
        });

        criteria.put("country", location -> {
            String country = getUserInput("Enter country to search: ");
            return location.getCountry().equalsIgnoreCase(country);
        });

        criteria.put("type", location -> {
            String type = getUserInput("Enter location type (e.g., BEACH, MOUNTAIN): ");
            try {
                return location.getType().toString().equalsIgnoreCase(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type entered.");
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

    public List<Location> searchLocations(int option) {
        String field = fieldOptions.get(option);
        if (field == null) {
            System.out.println("Invalid option selected.");
            return Collections.emptyList();
        }

        Predicate<Location> criteria = searchCriteria.get(field);
        return locations.values().stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    public void addLocation(Location location) {
        locations.put(location.getName(), location);
        saveToFile();
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }

    public void listAllLocations() {
        if (locations.isEmpty()) {
            System.out.println("No locations available.");
            return;
        }

        int index = 1;
        for (String locationName : locations.keySet()) {
            System.out.println(index + ". " + locationName);
            index++;
        }
    }

    public boolean removeLocation(String locationName) {
        if (locations.containsKey(locationName)) {
            locations.remove(locationName);
            saveToFile();
            return true;
        }
        return false;
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(locations);
        } catch (IOException e) {
            System.out.println("Error saving locations: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            locations.putAll((Map<String, Location>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading locations: " + e.getMessage());
        }
    }

    public Map<String, Location> getLocations() {
        return locations;
    }

    public int getFieldOptionsSize() {
        return fieldOptions.size();
    }
}
