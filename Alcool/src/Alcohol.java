/*
*
*   Copyright (C) 2020 Laurence Doucet
*   App that allows the management and record of alcohol
*
*/

public interface Alcohol {
    void setRating(double pRating);
    void addTag(Tag pTag);
    void displayTags();
    void displayInfo();
}
