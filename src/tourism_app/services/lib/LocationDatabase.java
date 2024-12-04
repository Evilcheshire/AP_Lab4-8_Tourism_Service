package tourism_app.services.lib;

import tourism_app.tour.location.Location;
import tourism_app.tour.location.LocationType;

import java.util.*;
import java.util.stream.Collectors;

public class LocationDatabase extends AbstractDatabase<Location> {

    public LocationDatabase() {
        super("locations.ser");
    }

    public List<Location> getFilteredLocations(List<LocationType> allowedTypes) {
        return data.values().stream()
                .filter(location -> allowedTypes.contains(location.getType()))
                .collect(Collectors.toList());
    }
}
