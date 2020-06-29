package main;

public class Client {

    public static void main(String[] args) {
        Wine redWine = new Wine("19 Crimes", 14.5, "Australia");
        redWine.setRating(4);
        redWine.setColour(WineColour.RED);

        Wine whiteWine = new Wine("Albert Bichot", 11.8, "France", WineColour.WHITE);

        Beer beer = new Beer("Amacord Gradisca", 5.2, "Italy");
        beer.setColour(BeerColour.BLONDE);
    }
}
