/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of wine
 *
 */

package main;

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
}
