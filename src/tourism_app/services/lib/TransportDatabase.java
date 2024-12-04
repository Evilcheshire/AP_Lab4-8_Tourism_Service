package tourism_app.services.lib;

import tourism_app.tour.Tour;
import tourism_app.tour.meal.Meal;
import tourism_app.tour.transport.Transport;

import java.io.*;
import java.util.*;

public class TransportDatabase extends AbstractDatabase<Transport> {
    public TransportDatabase() {
        super("transports.ser");
    }
}
