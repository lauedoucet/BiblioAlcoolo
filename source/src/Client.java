public class Client {

    public static void main(String[] args) {
        Alcohol redWine = new Alcohol("19 Crimes", 750, 14.5);
        redWine.addTag(Tag.Wine);
        redWine.addTag(Tag.Red);

        Alcohol beer = new Alcohol("La Gose IPA du Barachois", 355, 3.8);

        redWine.displayInfo();
        beer.displayInfo();
        beer.addTag(Tag.Beer);
        beer.displayTags();

    }
}
