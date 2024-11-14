package tourism_app.lib;

import java.io.Serializable;

public interface TourismDatabase extends Serializable {
    void saveToFile();
    void loadFromFile();
}
