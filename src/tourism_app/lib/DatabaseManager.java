package tourism_app.lib;

public class DatabaseManager {
    private final TourDatabase tourDatabase;
    private final UserDatabase userDatabase;
    private final LocationDatabase locationDatabase;
    private final TransportDatabase transportDatabase;
    private final MealDatabase mealDatabase;
    private final UserTourDatabase userTourDatabase;

    public DatabaseManager() {
        tourDatabase = new TourDatabase();
        userDatabase = new UserDatabase();
        locationDatabase = new LocationDatabase();
        transportDatabase = new TransportDatabase();
        mealDatabase = new MealDatabase();
        userTourDatabase = new UserTourDatabase();
    }

    public TourDatabase getTourDatabase() {
        return tourDatabase;
    }

    public UserDatabase getUserDatabase() {
        return userDatabase;
    }

    public LocationDatabase getLocationDatabase() {
        return locationDatabase;
    }

    public TransportDatabase getTransportDatabase() {
        return transportDatabase;
    }

    public MealDatabase getMealDatabase() {
        return mealDatabase;
    }

    public UserTourDatabase getUserTourDatabase() {
        return userTourDatabase;
    }

    public void loadAllDatabases() {
        tourDatabase.loadFromFile();
        userDatabase.loadFromFile();
        locationDatabase.loadFromFile();
        transportDatabase.loadFromFile();
        mealDatabase.loadFromFile();
        userTourDatabase.loadFromFile();
    }

    public void saveAllDatabases() {
        tourDatabase.saveToFile();
        userDatabase.saveToFile();
        locationDatabase.saveToFile();
        transportDatabase.saveToFile();
        mealDatabase.saveToFile();
        userTourDatabase.saveToFile();
    }
}
