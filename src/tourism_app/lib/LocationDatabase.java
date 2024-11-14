package tourism_app.lib;

import tourism_app.tour.location.Location;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class LocationDatabase implements TourismDatabase{
    private static final String FILE_PATH = "locations.ser";
    private final Map<String, Location> locations = new HashMap<>();

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

    public void listAllLocations() {
        locations.values().forEach(location -> System.out.println("Location: " + location.getName()));
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