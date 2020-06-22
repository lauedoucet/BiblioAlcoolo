/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of beer
 *
 */

package main;

public class Beer extends Alcohol {
    private BeerColour aColour;
    private int aIBU;

    public Beer(String pName, double pABV, String countryName) {
        super(pName, pABV, countryName);
        // aColour =
        aIBU = 0;
    }

    public Beer(String pName, double pABV, String countryName, BeerColour pColour) {
        super(pName, pABV, countryName);
        aColour = pColour;
        aIBU = 0;
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
    }
}
