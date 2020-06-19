/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents the collection of bottles
 *
 */

import java.util.ArrayList;
import java.util.HashMap;

public class Library {
    /************TODO: Implement as Flyweight***************/
    // HashMap of bottles with names as keys
    private String aName;
    private HashMap<String, Alcohol> bottlesByName;

    public Library() {
        bottlesByName = new HashMap<String, Alcohol>();
    }

    public Library(ArrayList<Alcohol> boozes) {
        for (Alcohol alcohol : boozes) {
            bottlesByName.put(alcohol.getName(), alcohol);
        }
    }

    public String getLibraryName() {
        return aName;
    }

    public Alcohol getAlcoholByName(String pName) {
        return bottlesByName.get(pName);
    }

    public boolean addBottle(Alcohol alcohol) {
        // if this returns a value that already has the same name, it returns false
        if (bottlesByName.putIfAbsent(alcohol.getName(), alcohol) == null) {
            return true;
        } else {
            return false;
        }
    }

    public void displayCollection() {
        System.out.println("Your library contains the following bottles:");
        bottlesByName.forEach((k,v) -> v.displayInfo());
    }
}