/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of wine
 *
 */

package main;

public class Wine extends FermentedAlcohol {

    public Wine(String pName, double pABV, String countryName) {
        super(pName, pABV, countryName);
    }
}
