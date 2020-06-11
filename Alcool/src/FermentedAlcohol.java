import java.util.ArrayList;

public class FermentedAlcohol implements Alcohol {
    protected String aName;
    protected double aABV;
    protected double aPrice;
    protected double aRating;
    protected ArrayList<Tag> aTags;


    public void setRating(double pRating) {
        aRating = pRating;
    }

    public void addTag(Tag pTag) {
        aTags.add(pTag);
    }

    public void displayTags() {
        for (Tag tag : aTags) {
            System.out.println(tag);
        }
    }

    public void displayInfo() {
        System.out.println(aName);
        System.out.println(aABV);
        System.out.println(aPrice);
        System.out.println(aRating);
        this.displayTags();
    }
}
