/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Concrete class that represents a bottle of beer
 *
 */

package main;

public class Beer extends FermentedAlcohol {

    public Beer(String pName, double pABV, String countryName) {
        super(pName, pABV, countryName);
    }
}
