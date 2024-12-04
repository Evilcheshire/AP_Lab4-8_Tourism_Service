package tourism_app.control.commands.creation;

import tourism_app.services.lib.TransportDatabase;
import tourism_app.control.commands.Command;
import tourism_app.tour.transport.*;
import tourism_app.services.utils.InputValidator;

public class CreateTransportCommand implements Command {
    private final TransportDatabase transportDB;
    private final InputValidator inputValidator;

    public CreateTransportCommand(TransportDatabase transportDB, InputValidator inputValidator) {
        this.transportDB = transportDB;
        this.inputValidator = inputValidator;
    }

    @Override
    public void execute() {
        System.out.println("Creating a new transport:");

        String name = inputValidator.getValidString("Enter the transport name: ");

        if (transportDB.getAllItemsAsMap().containsKey(name)) {
            System.out.println("A transport with this name already exists.");
            return;
        }

        System.out.println("Select the transport type:");
        TransportType[] types = TransportType.values();
        for (int i = 0; i < types.length; i++) {
            System.out.println((i + 1) + ". " + types[i].NAME);
        }

        int typeIndex = inputValidator.getValidIntInRange("Enter the number of the type: ",1, types.length);
        TransportType type = types[typeIndex - 1];

        double costPerDay = inputValidator.getValidPositiveDouble("Enter the cost per day: ");

        Transport newTransport = new Transport(name, type, costPerDay);

        transportDB.addItem(name, newTransport);

        System.out.println("Transport \"" + name + "\" has been successfully created!");
        System.out.println(newTransport);
    }

    @Override
    public String getName() {
        return "Create a new transport";
    }
}