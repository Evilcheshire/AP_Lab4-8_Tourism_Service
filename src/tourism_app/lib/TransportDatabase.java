package tourism_app.lib;

import tourism_app.tour.transport.Transport;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class TransportDatabase implements TourismDatabase{
    private static final String FILE_PATH = "transports.ser";
    private final Map<String, Transport> transports = new HashMap<>();

    public TransportDatabase() {
        loadFromFile();
    }

    public void addTransport(Transport transport) {
        transports.put(transport.getName(), transport);
        saveToFile();
    }

    public Transport getTransport(String name) {
        return transports.get(name);
    }

    public void listAllTransports() {
        transports.values().forEach(transport -> System.out.println("Transport: " + transport.getName() + ", Cost per day: " + transport.getCostPerDay()));
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
}
