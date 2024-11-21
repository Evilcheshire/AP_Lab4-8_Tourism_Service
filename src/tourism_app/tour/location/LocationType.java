package tourism_app.tour.location;

public enum LocationType {
    BEACH_RESORT("Beach resort"),   // for REST
    SPA_CENTRE("Spa centre"),     // for REST, TREATMENT
    MUSEUM("Museum"),         // for EXCURSION
    HISTORIC_SITE("Historic site"),  // for EXCURSION
    SANATORIUM("Sanatorium"),     // for TREATMENT
    FITNESS_CENTRE("Fitness centre"), // for TREATMENT
    SHOPPING_MALL("Shopping mall"),  // for SHOPPING
    STREET_MARKET("Street market"),  // for SHOPPING
    CRUISE_PORT("Cruise port"),    // for CRUISE
    ISLAND("Island"),         // for CRUISE
    CRUISE_SHIP("Cruise ship");    // for CRUISE

    public final String NAME;
    LocationType(String name){
        this.NAME = name;
    }
}
