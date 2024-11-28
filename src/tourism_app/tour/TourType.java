package tourism_app.tour;

import tourism_app.tour.location.LocationType;

import java.util.Arrays;
import java.util.List;

public enum TourType {
    REST(100, "Rest",
            Arrays.asList(LocationType.BEACH_RESORT, LocationType.SPA_CENTRE)),
    EXCURSION(50, "Excursion",
            Arrays.asList(LocationType.MUSEUM, LocationType.HISTORIC_SITE)),
    TREATMENT(150, "Treatment",
            Arrays.asList(LocationType.SPA_CENTRE, LocationType.SANATORIUM, LocationType.FITNESS_CENTRE)),
    SHOPPING(50, "Shopping",
            Arrays.asList(LocationType.SHOPPING_MALL, LocationType.STREET_MARKET)),
    CRUISE(200, "Cruise",
            Arrays.asList(LocationType.CRUISE_PORT, LocationType.ISLAND, LocationType.CRUISE_SHIP));

    public final int BASE_PRICE;
    public final String NAME;
    private final List<LocationType> allowedLocationTypes;

    TourType(int price, String name, List<LocationType> allowedLocationTypes) {
        this.BASE_PRICE = price;
        this.NAME = name;
        this.allowedLocationTypes = allowedLocationTypes;
    }

    public List<LocationType> getAllowedLocationTypes() {
        return allowedLocationTypes;
    }
}