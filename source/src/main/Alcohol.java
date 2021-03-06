/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   abstract Class represents an alcohol bottle
 *
 */

package main;

import java.io.Serializable;

public class Alcohol implements Serializable {

    private String aName;
    private int aRating;
    private double aABV;
    private String aCountry;

    public Alcohol(String pName, double pABV, String pCountry) {
        aName = pName;
        aRating = 0;
        aABV = pABV;
        aCountry = pCountry;
    }

    public String getName() {
        return aName;
    }

    /**
     * @return 0 if the alcohol is not rated yet
     */
    public int getRating() {
        return aRating;
    }

    public double getABV() { return aABV; }

    public String getCountry() {
        return aCountry;
    }

    /**
     * Rating is 1-5 inclusive, 0 represents no rating
     * @param pRating > 0 && pRating < 6
     */
    public void setRating (int pRating) {
        assert pRating > 0 && pRating < 6;
        aRating = pRating;
    }

    public void setName(String aName) {
        this.aName = aName;
    }

    /**
     * @param pABV > 0
     */
    public void setABV(double pABV) {
        assert pABV > 0;
        aABV = pABV;
    }

    public void setCountry(String pCountry) {
        aCountry = pCountry;
    }

    public void displayInfo() {
        System.out.println("Name:       " + aName);
        System.out.println("ABV:        " + aABV + "%");
        System.out.println("Country:    " + aCountry);
        if (aRating > 0) {
            System.out.println("Rating:     " + aRating + "/5");
        } else {
            System.out.println("No rating yet.");
        }
    }
}