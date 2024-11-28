package tourism_app.services.lib;

import tourism_app.tour.Tour;

import java.io.*;
import java.util.*;

public class TourDatabase {
    private static final String FILE_PATH = "tours.ser";
    private final List<Tour> tours = new ArrayList<>();

    public TourDatabase() {
        loadFromFile();
    }

    public void addTour(Tour tour) {
        tours.add(tour);
        saveToFile();
    }

    public boolean removeTour(String tourName, Runnable onTourRemoved) {
        boolean removed = tours.removeIf(tour -> tour.getName().equals(tourName));
        if (removed) {
            saveToFile();
            if (onTourRemoved != null) {
                onTourRemoved.run();
            }
        }
        return removed;
    }

    public void listAllTours() {
        if (tours.isEmpty()) {
            System.out.println("No tours available.");
        } else {
            tours.forEach(tour -> System.out.println(tour.toString() + "\n"));
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH,false))) {
            out.writeObject(tours);
        } catch (IOException e) {
            System.out.println("Error saving tours: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            tours.addAll((List<Tour>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tours: " + e.getMessage());
        }
    }

    public List<Tour> getTours() {
        return new ArrayList<>(tours);
    }
}
