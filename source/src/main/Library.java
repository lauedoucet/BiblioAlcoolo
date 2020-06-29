/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents the collection of bottles
 *
 */

package main;
import java.util.HashMap;
import java.util.Iterator;

public class Library implements Iterable<Alcohol> {
    private String aName;
    private HashMap<String, Alcohol> bottlesByName;

    public Library() {
        bottlesByName = new HashMap<String, Alcohol>();
    }

    public Library(String pName) {
        aName = pName;
        bottlesByName = new HashMap<String, Alcohol>();
    }

    public String getName() {
        return aName;
    }

    public int getSize() {
        return bottlesByName.size();
    }

    public void setName(String pName) {
        aName = pName;
    }

    /**
     * Overloaded methods
     * Implements Flyweight DP for the library class
     * @return the alcohol object with the parameters, or creates a new one with those parameters
     */
    public Alcohol addAlcohol(String pName, double pABV, String pCountry) {
        Alcohol alcohol = bottlesByName.get(pName);
        if (alcohol == null) {
            alcohol = new Alcohol(pName, pABV, pCountry);
            bottlesByName.put(pName, alcohol);
        }
        return alcohol;
    }

    /**
     * Adds alcohol object if not already present in the Library
     * @param alcohol to be added to library
     * @return returns true if the alcohol was added, returns false if bottle already present
     */
    public boolean addAlcohol(Alcohol alcohol) {
        if (bottlesByName.putIfAbsent(alcohol.getName(), alcohol) == null) {
            return true;
        } else {
            return false;
        }
    }

    public boolean addAll(Alcohol... alcoholList) {
        int i = 0, j = 0;
        for (Alcohol alcohol : alcoholList) {
            if (addAlcohol(alcohol)) {
                i++;
            }
            j++;
        }
        if (i == j) {
            return true;
        } else {
            return false;
        }
    }

    public Alcohol getByName(String name) {
        return bottlesByName.get(name);
    }

    /**
     * TODO: getByCountry or getByRating?
     * TODO: sorting methods
     */

    public void displayCollection() {
        System.out.println("Your library contains the following bottles:");
        bottlesByName.forEach((k,v) -> {
            v.displayInfo();
            System.out.println("/****************************************/");
        });
    }

    @Override
    public Iterator<Alcohol> iterator() {
        return bottlesByName.values().iterator();
    }
}