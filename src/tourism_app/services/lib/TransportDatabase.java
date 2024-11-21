package tourism_app.services.lib;

import tourism_app.tour.transport.Transport;

import java.io.*;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class TransportDatabase {
    private static final String FILE_PATH = "transports.ser";
    private final Map<String, Transport> transports = new LinkedHashMap<>();

    private final Map<Integer, String> fieldOptions = Map.of(
            1, "name",
            2, "type",
            3, "cost"
    );

    private final Map<String, Predicate<Transport>> searchCriteria;

    public TransportDatabase() {
        loadFromFile();
        searchCriteria = initializeSearchCriteria();
    }

    private Map<String, Predicate<Transport>> initializeSearchCriteria() {
        Map<String, Predicate<Transport>> criteria = new HashMap<>();

        criteria.put("name", transport -> {
            String name = getUserInput("Enter name to search: ");
            return transport.getName().equalsIgnoreCase(name);
        });

        criteria.put("type", transport -> {
            String type = getUserInput("Enter transport type (e.g., BUS, TRAIN): ");
            try {
                return transport.getType().toString().equalsIgnoreCase(type);
            } catch (IllegalArgumentException e) {
                System.out.println("Invalid type entered.");
                return false;
            }
        });

        criteria.put("cost", transport -> {
            String input = getUserInput("Enter maximum cost per day: ");
            try {
                double maxCost = Double.parseDouble(input);
                return transport.getCostPerDay() <= maxCost;
            } catch (NumberFormatException e) {
                System.out.println("Invalid cost entered.");
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

    public List<Transport> searchTransports(int option) {
        String field = fieldOptions.get(option);
        if (field == null) {
            System.out.println("Invalid option selected.");
            return Collections.emptyList();
        }

        Predicate<Transport> criteria = searchCriteria.get(field);
        return transports.values().stream()
                .filter(criteria)
                .collect(Collectors.toList());
    }

    public void addTransport(Transport transport) {
        transports.put(transport.getName(), transport);
        saveToFile();
    }

    public boolean removeTransport(String name) {
        if (transports.remove(name) != null) {
            saveToFile();
            return true;
        }
        return false;
    }

    public void listAllTransports() {
        if (transports.isEmpty()) {
            System.out.println("No transports available.");
        } else {
            transports.values().forEach(transport ->
                    System.out.println("Transport: " + transport.getName() + ", Cost per day: " + transport.getCostPerDay()));
        }
    }

    public void displaySearchFields() {
        System.out.println("You would like to search:");
        fieldOptions.forEach((key, value) -> System.out.println(key + ". By " + value));
    }

    public Transport getTransport(String name) {
        return transports.get(name);
    }

    public void saveToFile() {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(FILE_PATH))) {
            out.writeObject(transports);
        } catch (IOException e) {
            System.out.println("Error saving transports: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    public void loadFromFile() {
        File file = new File(FILE_PATH);
        if (!file.exists()) return;
        try (ObjectInputStream in = new ObjectInputStream(new FileInputStream(file))) {
            transports.putAll((Map<String, Transport>) in.readObject());
        } catch (IOException | ClassNotFoundException e) {
            System.out.println("Error loading transports: " + e.getMessage());
        }
    }

    public Map<String, Transport> getTransports() {
        return transports;
    }

    public int getFieldOptionsSize() {
        return fieldOptions.size();
    }
}
