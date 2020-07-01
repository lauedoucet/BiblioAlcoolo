/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of wine
 *
 */

package main;

import java.util.*;

public class Wine extends Alcohol {
    private WineColour aColour;
    private String aVariety;

    public Wine(String pName, double pABV, String countryName) {
        super(pName, pABV, countryName);
        aColour = WineColour.NULL;
        aVariety = "";
    }

    public Wine(String pName, double pABV, String countryName, WineColour pColour, String pVariety) {
        super(pName, pABV, countryName);
        aColour = pColour;
        aVariety = pVariety;
    }

    public Wine(String pName, double pABV, String countryName, WineColour pColour) {
        super(pName, pABV, countryName);
        aColour = pColour;
        aVariety = "";
    }

    public void setVariety(String pVariety) {
        aVariety = pVariety;
    }

    public void setColour(WineColour pColour){
        aColour = pColour;
    }

    public String getVariety() {
        return aVariety;
    }

    public WineColour getColour() {
        return aColour;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        if (aColour != WineColour.NULL) {
            System.out.println("Colour:    " + aColour.name().toLowerCase());
        }
        if (aVariety != null) {
            System.out.println("Variety:    " + aVariety);
        }
    }

    public static LinkedHashMap<String, Wine> sortByColour(LinkedHashMap<String, Wine> wines) {
        Set<Map.Entry<String, Wine>> entries = wines.entrySet();
        List<Map.Entry<String, Wine>> entriesList = new ArrayList<>(entries);
        Collections.sort(entriesList, new Comparator<Map.Entry<String, Wine>>() {
            @Override
            public int compare(Map.Entry<String, Wine> entry1, Map.Entry<String, Wine> entry2) {
                return entry1.getValue().getColour().compareTo(entry2.getValue().getColour());
            }
        });
        LinkedHashMap<String, Wine> sortedBottles = new LinkedHashMap<>();
        for (Map.Entry<String, Wine> entry : entriesList) {
            sortedBottles.put(entry.getKey(), entry.getValue());
        }

        return sortedBottles;
    }
}
