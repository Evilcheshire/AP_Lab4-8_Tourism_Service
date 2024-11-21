package tourism_app.services.lib;

import tourism_app.tour.Tour;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TourDatabase {
    private static final String FILE_PATH = "tours.ser";
    private final List<Tour> tours = new ArrayList<>();

    private final Map<Integer, String> fieldOptions = Map.of(
            1, "name",
            2, "location",
            3, "type",
            4, "price",
            5, "start date"
    );

    private final Map<String, Predicate<Tour>> searchCriteria;

    public TourDatabase() {
        loadFromFile();
        searchCriteria = initializeSearchCriteria();
    }

    private Map<String, Predicate<Tour>> initializeSearchCriteria() {
        Map<String, Predicate<Tour>> criteria = new HashMap<>();

        criteria.put("name", tour -> {
            String name = getUserInput("Enter tour name: ");
            return tour.getName().equalsIgnoreCase(name);
        });

        criteria.put("location", tour -> {
            String location = getUserInput("Enter location name: ");
            return tour.getLocation().getName().equalsIgnoreCase(location);
        });

        criteria.put("type", tour -> {
            String type = getUserInput("Enter tour type (e.g., FAMILY, ADVENTURE): ");
            try {
                return tour.getType().toString().equalsIgnoreCase(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid tour type entered.");
                return false;
            }
        });

        criteria.put("price", tour -> {
            try {
                double minPrice = Double.parseDouble(getUserInput("Enter minimum price: "));
                double maxPrice = Double.parseDouble(getUserInput("Enter maximum price: "));
                return tour.getTotalPrice() >= minPrice && tour.getTotalPrice() <= maxPrice;
            } catch (NumberFormatException e) {
                System.out.println("Invalid price entered.");
                return false;
            }
        });

        criteria.put("start date", tour -> {
            try {
                String dateInput = getUserInput("Enter start date (format: yyyy-MM-dd): ");
                SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
                dateFormat.setLenient(false);
                Date startDate = dateFormat.parse(dateInput);
                return !tour.getStartDate().before(startDate);
            } catch (ParseException e) {
                System.out.println("Invalid date format. Please use yyyy-MM-dd.");
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

    public List<Tour> searchTours(int option) {
        String field = fieldOptions.get(option);
        if (field == null) {
            System.out.println("Invalid option selected.");
            return Collections.emptyList();
        }

        Predicate<Tour> criteria = searchCriteria.get(field);
        return tours.stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    public void addTour(Tour tour) {
        tours.add(tour);
        saveToFile();
    }

    public boolean removeTour(String tourName) {
        boolean removed = tours.removeIf(tour -> tour.getName().equals(tourName));
        if (removed) {
            saveToFile();
        }
        return removed;
    }

    public void listAllTours() {
        if (tours.isEmpty()) {
            System.out.println("No tours available.");
        } else {
            tours.forEach(tour ->
                    System.out.println("Tour: " + tour.getName() + " - " + tour.getType() + " - $" + tour.getTotalPrice()));
        }
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
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

    public int getFieldOptionsSize() {
        return fieldOptions.size();
    }
}
