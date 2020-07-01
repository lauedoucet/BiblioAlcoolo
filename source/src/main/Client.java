package main;

import java.util.LinkedHashMap;

public class Client {
    public static void main(String[] args) {
        Library library = new Library("Example");
        Beer schrad = new Beer("SchraderBrau", 6.2, "United States", BeerColour.GOLDEN);
        Beer mac = new Beer("MacKroken", 10.8, "Canada", BeerColour.BROWN, 18);
        Wine red = new Wine("19 Crimes", 14.5, "Australia", WineColour.RED);
        Wine white = new Wine("Albert Bichot", 13, "France", WineColour.WHITE, "Chardonnay");
        Alcohol alcohol = new Alcohol("Aberfeldy 21", 40, "United Kingdom");
        Wine otherWine = new Wine("Example", 14, "France", WineColour.ORANGE);
        library.addAll(schrad, mac, red, white, alcohol);
        //library.displayCollection();
        library.sortByName();
        //library.displayCollection();

        LinkedHashMap<String, Wine> wines = library.getOnlyWines();
        wines.put(otherWine.getName(), otherWine);
        wines = Wine.sortByColour(wines);
        LinkedHashMap<String, Beer> beers = library.getOnlyBeers();

        for (Wine wine : wines.values()) {
            wine.displayInfo();
            System.out.println("*********************************");
        }
    }
}
