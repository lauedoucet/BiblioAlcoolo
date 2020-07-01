/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of beer
 *
 */

package main;

import java.util.*;

public class Beer extends Alcohol {
    private BeerColour aColour;
    private int aIBU;

    public Beer(String pName, double pABV, String countryName) {
        super(pName, pABV, countryName);
        aColour = BeerColour.NULL;
        aIBU = 0;
    }

    public Beer(String pName, double pABV, String countryName, BeerColour pColour) {
        super(pName, pABV, countryName);
        aColour = pColour;
        aIBU = 0;
    }

    public Beer(String pName, double pABV, String countryName, BeerColour pColour, int pIBU) {
        super(pName, pABV, countryName);
        aColour = pColour;
        aIBU = pIBU;
    }

    public void setColour(BeerColour pColour) {
        aColour = pColour;
    }

    /**
     * @param pIBU > 0
     */
    public void setIBU(int pIBU) {
        assert pIBU > 0;
        aIBU = pIBU;
    }

    public BeerColour getColour() {
        return aColour;
    }

    public int getIBU() {
        return aIBU;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Colour:     " + aColour.name().toLowerCase());
        if (aIBU != 0) {
            System.out.println("IBU:    " + aIBU);
        }
    }

    public static LinkedHashMap<String, Beer> sortByColour(LinkedHashMap<String, Beer> beers) {
        Set<Map.Entry<String, Beer>> entries = beers.entrySet();
        List<Map.Entry<String, Beer>> entriesList = new ArrayList<>(entries);
        Collections.sort(entriesList, new Comparator<Map.Entry<String, Beer>>() {
            @Override
            public int compare(Map.Entry<String, Beer> entry1, Map.Entry<String, Beer> entry2) {
                return entry1.getValue().getColour().compareTo(entry2.getValue().getColour());
            }
        });
        LinkedHashMap<String, Beer> sortedBottles = new LinkedHashMap<>();
        for (Map.Entry<String, Beer> entry : entriesList) {
            sortedBottles.put(entry.getKey(), entry.getValue());
        }

        return sortedBottles;
    }
}
