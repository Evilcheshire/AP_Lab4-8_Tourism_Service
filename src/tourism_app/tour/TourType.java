package tourism_app.tour;

public enum TourType {
    REST(100), EXCURSION(50), TREATMENT(150), SHOPPING(50), CRUISE(200);

    public final int BASE_PRICE;
    TourType(int price){
        this.BASE_PRICE = price;
    }
}