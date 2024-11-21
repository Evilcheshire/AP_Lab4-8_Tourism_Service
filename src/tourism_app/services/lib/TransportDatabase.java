package tourism_app.services.lib;

import tourism_app.tour.transport.Transport;

import java.io.*;
import java.util.*;

public class TransportDatabase {
    private static final String FILE_PATH = "transports.ser";
    private final Map<String, Transport> transports = new LinkedHashMap<>();

    public TransportDatabase() {
        loadFromFile();
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

    public List<Transport> getAllTransports() {
        return new ArrayList<>(transports.values());
    }
}
