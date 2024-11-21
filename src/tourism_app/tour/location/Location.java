package tourism_app.tour.location;

import java.io.Serializable;

public class Location  implements Serializable {
    private String name;
    private String country;
    private LocationType type;
    private String description;

    public Location (String name, String country, LocationType type, String description){
        this.country = country;
        this.name = name;
        this.type = type;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getCountry() {
        return country;
    }

    public LocationType getType() {
        return type;
    }

    public String getDescription() {
        return description;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setType(LocationType type) {
        this.type = type;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String toString() {
        return String.format("Location: %s\nCountry: %s\nType: %s\nDescription: %s",
                name, country, type, description);
    }

}
