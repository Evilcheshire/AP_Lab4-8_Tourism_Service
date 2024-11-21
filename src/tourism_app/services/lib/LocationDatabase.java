package tourism_app.services.lib;

import tourism_app.tour.location.Location;

import java.io.*;
import java.util.*;

public class LocationDatabase {
    private static final String FILE_PATH = "locations.ser";
    private final Map<String, Location> locations = new LinkedHashMap<>();

    public LocationDatabase() {
        loadFromFile();
    }

    public void addLocation(Location location) {
        locations.put(location.getName(), location);
        saveToFile();
    }

    public Location getLocation(String name) {
        return locations.get(name);
    }

    public Map<String, Location> getLocations() {
        return locations;
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
}
