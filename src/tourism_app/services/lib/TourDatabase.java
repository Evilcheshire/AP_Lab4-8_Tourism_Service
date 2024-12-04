package tourism_app.services.lib;

import tourism_app.tour.Tour;

import java.io.*;
import java.util.*;

public class TourDatabase extends AbstractDatabase<Tour> {
    public TourDatabase() {
        super("tours.ser");
    }
}
