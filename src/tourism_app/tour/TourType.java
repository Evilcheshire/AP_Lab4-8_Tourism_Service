package tourism_app.tour;

public enum TourType {
    REST(100, "Rest"),
    EXCURSION(50, "Excursion"),
    TREATMENT(150, "Treatment"),
    SHOPPING(50, "Shopping"),
    CRUISE(200, "Cruise");

    public final int BASE_PRICE;
    public final String NAME;
    TourType(int price, String name){
        this.BASE_PRICE = price;
        this.NAME = name;
    }
}