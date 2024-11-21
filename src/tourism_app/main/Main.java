package tourism_app.main;

import tourism_app.services.lib.*;
import tourism_app.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();
        dbManager.loadAllDatabases();

        //dbManager.getUserDatabase().registerUser("admin1", "admin1pass", UserType.ADMIN);

        MainMenu mainMenu = new MainMenu(dbManager);

        mainMenu.start();

    }
}
