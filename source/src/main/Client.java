package main;

public class Client {

    public static void main(String[] args) {
        FermentedAlcohol redWine = new FermentedAlcohol("19 Crimes", 14.5, "Australia");
        redWine.displayInfo();
        redWine.setRating(4);
        redWine.displayInfo();

    }
}
