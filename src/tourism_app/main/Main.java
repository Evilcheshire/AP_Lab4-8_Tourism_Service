package tourism_app.main;

import tourism_app.lib.UserDatabase;
import tourism_app.menu.MainMenu;

public class Main {
    public static void main(String[] args) {
        UserDatabase userDatabase = new UserDatabase();

        MainMenu mainMenu = new MainMenu(userDatabase);

        mainMenu.start();

    }
}
