package tourism_app.lib;

import tourism_app.tour.Tour;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class TourDatabase {
    private static final String FILE_PATH = "availableTours.ser";
    private final List<Tour> availableTours;

    public TourDatabase() {
        availableTours = new ArrayList<>();
        loadFromFile();
    }

    public void addTour(Tour tour) {
        availableTours.add(tour);
        saveToFile();
    }

    public boolean removeTour(Tour tour) {
        boolean removed = availableTours.remove(tour);
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    public List<Tour> getAvailableTours() {
        return new ArrayList<>(availableTours);
    }

    private void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(availableTours);
        } catch (IOException e) {
            System.out.println("Error saving tours data: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    private void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            availableTours.addAll((List<Tour>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading tours data: " + e.getMessage());
        }
    }

    public void listAllTours() {
        if (availableTours.isEmpty()) {
            System.out.println("No tours available.");
        } else {
            for (Tour tour : availableTours) {
                System.out.println(tour.getName() + " - " + tour.getType() + " - $" + tour.getTotalPrice());
            }
        }
    }
}

