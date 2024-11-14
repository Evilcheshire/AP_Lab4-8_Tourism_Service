package tourism_app.main;

import tourism_app.lib.*;
import tourism_app.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        DatabaseManager dbManager = new DatabaseManager();

        MainMenu mainMenu = new MainMenu(dbManager);

        mainMenu.start();

    }
}
