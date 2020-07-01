/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents the collection of bottles
 *
 */

package main;
import java.util.*;

public class Library implements Iterable<Alcohol> {
    private String aName;
    private LinkedHashMap<String, Alcohol> aBottles;

    public Library() {
        aBottles = new LinkedHashMap<String, Alcohol>();
    }

    public Library(String pName) {
        aName = pName;
        aBottles = new LinkedHashMap<String, Alcohol>();
    }

    public String getName() {
        return aName;
    }

    public int getSize() {
        return aBottles.size();
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
        Alcohol alcohol = aBottles.get(pName);
        if (alcohol == null) {
            alcohol = new Alcohol(pName, pABV, pCountry);
            aBottles.put(pName, alcohol);
        }
        return alcohol;
    }

    /**
     * Adds alcohol object if not already present in the Library
     * @param alcohol to be added to library
     * @return returns true if the alcohol was added, returns false if bottle already present
     */
    public boolean addAlcohol(Alcohol alcohol) {
        if (aBottles.putIfAbsent(alcohol.getName(), alcohol) == null) {
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

    public void removeAlcohol(Alcohol alcohol) {
        aBottles.remove(alcohol.getName());
    }

    public Alcohol getByName(String name) {
        return aBottles.get(name);
    }

    /**
     * TODO: getByCountry or getByRating?
     * TODO: sorting methods
     */

    /**
     * Sorts library by name in alphabetical order
     */
    public void sortByName() {
        Set<Map.Entry<String, Alcohol>> entries = aBottles.entrySet();
        List<Map.Entry<String, Alcohol>> entriesList = new ArrayList<>(entries);
        Collections.sort(entriesList, new Comparator<Map.Entry<String, Alcohol>>() {
            @Override
            public int compare(Map.Entry<String, Alcohol> alcohol1, Map.Entry<String, Alcohol> alcohol2) {
                return alcohol1.getValue().getName().compareToIgnoreCase(alcohol2.getValue().getName());
            }
        });
        LinkedHashMap sortedBottles = new LinkedHashMap();
        for (Map.Entry<String, Alcohol> entry : entriesList) {
            sortedBottles.put(entry.getKey(), entry.getValue());
        }

        this.aBottles = sortedBottles;
    }

    /**
     * Sorts the library from lowest to highest ABV
     */
    public void sortByABV() {
        Set<Map.Entry<String, Alcohol>> entries = aBottles.entrySet();
        List<Map.Entry<String, Alcohol>> entriesList = new ArrayList<>(entries);
        Collections.sort(entriesList, new Comparator<Map.Entry<String, Alcohol>>() {
            @Override
            public int compare(Map.Entry<String, Alcohol> entry1, Map.Entry<String, Alcohol> entry2) {
                if (entry1.getValue().getABV() > entry2.getValue().getABV()) {
                    return 1;
                } else if (entry1.getValue().getABV() < entry2.getValue().getABV()) {
                    return -1;
                } else {
                    return 0;
                }
            }
        });
        LinkedHashMap sortedBottles = new LinkedHashMap();
        for (Map.Entry<String, Alcohol> entry : entriesList) {
            sortedBottles.put(entry.getKey(), entry.getValue());
        }

        this.aBottles = sortedBottles;
    }

    public void sortByCountry() {
        Set<Map.Entry<String, Alcohol>> entries = aBottles.entrySet();
        List<Map.Entry<String, Alcohol>> entriesList = new ArrayList<>(entries);
        Collections.sort(entriesList, new Comparator<Map.Entry<String, Alcohol>>() {
            @Override
            public int compare(Map.Entry<String, Alcohol> entry1, Map.Entry<String, Alcohol> entry2) {
                return entry1.getValue().getCountry().compareToIgnoreCase(entry2.getValue().getCountry());
            }
        });
        LinkedHashMap sortedBottles = new LinkedHashMap();
        for (Map.Entry<String, Alcohol> entry : entriesList) {
            sortedBottles.put(entry.getKey(), entry.getValue());
        }

        this.aBottles = sortedBottles;
    }

    public LinkedHashMap<String, Wine> getOnlyWines() {
        LinkedHashMap<String, Wine> wines = new LinkedHashMap<>();
        for (Alcohol alcohol : this) {
            if (alcohol.getClass() == Wine.class) {
                Wine wine = (Wine) alcohol;
                wines.put(wine.getName(), wine);
            }
        }

        return wines;
    }

    public LinkedHashMap<String, Beer> getOnlyBeers() {
        LinkedHashMap<String, Beer> beers = new LinkedHashMap<>();
        for (Alcohol alcohol : this) {
            if (alcohol.getClass() == Beer.class) {
                Beer beer = (Beer) alcohol;
                beers.put(beer.getName(), beer);
            }
        }
        return beers;
    }

    public void displayCollection() {
        System.out.println("Your library contains the following bottles:");
        aBottles.forEach((k, v) -> {
            v.displayInfo();
            System.out.println("/****************************************/");
        });
    }

    @Override
    public Iterator<Alcohol> iterator() {
        return aBottles.values().iterator();
    }
}