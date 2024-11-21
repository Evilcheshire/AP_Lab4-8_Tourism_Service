package tourism_app.tour.transport;

public enum TransportType {
    BUS("Bus"),
    TRAIN("Train"),
    PLANE("Plane"),
    CRUISE_SHIP("Cruise ship");

    public final String NAME;
    TransportType(String name){
        this.NAME = name;
    }
}
