/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of wine
 *
 */

package main;

public class Wine extends Alcohol {
    private WineVariety aVariety;
    private WineVariety.WineColour aColour;

    public Wine(String pName, double pABV, String countryName) {
        super(pName, pABV, countryName);
        aVariety = WineVariety.NULL;
        aColour = WineVariety.WineColour.NULL;
    }

    public Wine(String pName, double pABV, String countryName, WineVariety pVariety) {
        super(pName, pABV, countryName);
        aVariety = pVariety;
        aColour = pVariety.aColour;
    }

    public Wine(String pName, double pABV, String countryName, WineVariety.WineColour pColour) {
        super(pName, pABV, countryName);
        aVariety = WineVariety.NULL;
        aColour = pColour;
    }

    public void setVariety(WineVariety pVariety) {
        aVariety = pVariety;
    }

    public void setColour(WineVariety.WineColour pColour){
        aColour = pColour;
    }

    public WineVariety getVariety() {
        return aVariety;
    }

    public WineVariety.WineColour getColour() {
        return aColour;
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        System.out.println("Colour:    " + aColour.name().toLowerCase());
        if (aVariety != WineVariety.NULL) {
            System.out.println("Variety:    " + aVariety.name().toLowerCase());
        }
    }
}
