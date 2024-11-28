package tourism_app.main;

import tourism_app.services.lib.*;
import tourism_app.control.menu.MainMenu;
import tourism_app.tour.location.Location;
import tourism_app.tour.location.LocationType;
import tourism_app.tour.meal.Meal;
import tourism_app.tour.transport.Transport;
import tourism_app.tour.transport.TransportType;
import tourism_app.users.User;
import tourism_app.users.UserType;

import java.util.Arrays;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.loadAllDatabases();

        MainMenu mainMenu = new MainMenu(dbManager);

        dbManager.getUserDatabase().registerUser("admin1", "admin1pass", UserType.ADMIN);

        dbManager.getTransportDatabase().addTransport(new Transport("Bus", TransportType.BUS, 10));
        dbManager.getTransportDatabase().addTransport(new Transport("Plane", TransportType.PLANE, 20));
        dbManager.getTransportDatabase().addTransport(new Transport("Train", TransportType.TRAIN, 15));
        dbManager.getTransportDatabase().addTransport(new Transport("Cruise ship", TransportType.CRUISE_SHIP, 50));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Malibu Beach Resort",
                "USA",
                LocationType.BEACH_RESORT,
                "A luxurious resort located on the sunny beaches of Malibu."
        ));
        dbManager.getLocationDatabase().addLocation(new Location(
                "Blue Lagoon Spa",
                "Iceland",
                LocationType.SPA_CENTRE,
                "A geothermal spa offering relaxation and rejuvenation in stunning surroundings."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Louvre Museum",
                "France",
                LocationType.MUSEUM,
                "World's largest art museum and a historic monument in Paris."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Great Wall of China",
                "China",
                LocationType.HISTORIC_SITE,
                "A historic site stretching over thousands of miles, symbolizing China's strength and endurance."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Karlovy Vary Sanatorium",
                "Czech Republic",
                LocationType.SANATORIUM,
                "A renowned sanatorium specializing in natural mineral water treatments."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Gold's Gym Venice",
                "USA",
                LocationType.FITNESS_CENTRE,
                "The iconic fitness center where modern bodybuilding began."
        ));

        dbManager.getLocationDatabase().addLocation( new Location(
                "Dubai Mall",
                "UAE",
                LocationType.SHOPPING_MALL,
                "The largest shopping mall in the world, featuring countless shops and attractions."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Chatuchak Weekend Market",
                "Thailand",
                LocationType.STREET_MARKET,
                "One of the largest street markets in the world, offering a variety of goods and local delicacies."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Port of Miami",
                "USA",
                LocationType.CRUISE_PORT,
                "The cruise capital of the world, connecting travelers to Caribbean destinations."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Santorini",
                "Greece",
                LocationType.ISLAND,
                "A picturesque island known for its white-washed houses and stunning sunsets."
        ));

        dbManager.getLocationDatabase().addLocation(new Location(
                "Symphony of the Seas",
                "International Waters",
                LocationType.CRUISE_SHIP,
                "The largest cruise ship in the world, offering luxury amenities and entertainment."
        ));

        dbManager.getMealDatabase().addMeal(new Meal(
                "Continental Breakfast",
                Arrays.asList("Vegetarian", "Non-Vegetarian"),
                15.99,
                1
        ));

        dbManager.getMealDatabase().addMeal(new Meal(
                "All-Inclusive Buffet",
                Arrays.asList("Vegan", "Vegetarian", "Non-Vegetarian"),
                49.99,
                3
        ));

        dbManager.getMealDatabase().addMeal(new Meal(
                "Seafood Platter",
                Arrays.asList("Non-Vegetarian"),
                29.99,
                2
        ));

        mainMenu.start();

    }
}
