/*
 *
 *   Copyright (C) 2020 Laurence Doucet
 *   App that allows the management and record of alcohol
 *   Class represents an alcohol bottle
 *
 */

package main;

import java.util.ArrayList;

public class Alcohol {

    private String aName;
    private int aSize;
    private double aABV;
    private ArrayList<Tag> tags;

    public Alcohol(String pName, int pSize, double pABV) {
        aName = pName;
        aSize = pSize;
        aABV = pABV;
        tags = new ArrayList<Tag>();
    }

    public String getName() {
        return aName;
    }

    public int getSize() {  return aSize;   }

    public double getABV() { return aABV; }

    public void setABV(double pABV) {
        aABV = pABV;
    }

    public void addTag(Tag pTag) {
        tags.add(pTag);
    }

    public void displayTags() {
        for (Tag tag : tags) {
            System.out.print("#" + tag.name() + " ");
        }
        System.out.println(" ");
    }

    public void displayInfo() {
        System.out.println("Name:   " + aName);
        System.out.println("Size:   " + aSize + " ml");
        System.out.println("ABV:   " + aABV + "%");
        displayTags();

    }
}