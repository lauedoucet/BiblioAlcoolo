package main;

public enum WineVariety {
    BARBERA(WineColour.RED),
    BRUNELLO(WineColour.RED),
    CABERNET_FRANC(WineColour.RED),
    CABERNET_SAUVIGNON(WineColour.RED),
    CABERNET_SAUVIGNON_ROSE(WineColour.ROSÉ),
    CARIGNAN(WineColour.RED),
    CARMENERE(WineColour.RED),
    CHARBONO(WineColour.RED),
    CHAMPAGNE(WineColour.SPARKLING),
    CHARDONNAY(WineColour.WHITE),
    CHENIN_BLANC(WineColour.WHITE),
    DOLCETTO(WineColour.RED),
    FUMÉ_BLANC(WineColour.WHITE),
    GAMAY(WineColour.RED),
    GEWURZTRAMINER(WineColour.WHITE),
    GRENACHE(WineColour.RED),
    GRENACHE_ROSE(WineColour.ROSÉ),
    GRUNER_VELTLINER(WineColour.WHITE),
    MALBEC(WineColour.RED),
    MARSANNE(WineColour.WHITE),
    MERLOT(WineColour.RED),
    MOURVEDRE(WineColour.RED),
    MOURVEDRE_ROSE(WineColour.ROSÉ),
    MUSCAT(WineColour.WHITE),
    NEBBIOLO(WineColour.RED),
    PETITE_SIRAH(WineColour.RED),
    PINOT_BLANC(WineColour.WHITE),
    PINOT_GRIGIO(WineColour.WHITE),
    PINOT_NOIR(WineColour.RED),
    PINOT_NOIR_ROSE(WineColour.ROSÉ),
    PROVENCE_ROSE(WineColour.ROSÉ),
    RIESLING(WineColour.WHITE),
    SANGIOVESE(WineColour.RED),
    SANGIOVESE_ROSE(WineColour.ROSÉ),
    SAUVIGNON_BLANC(WineColour.WHITE),
    SEMILLON(WineColour.WHITE),
    SHIRAZ(WineColour.RED),
    SYRAH(WineColour.ROSÉ),
    TAVEL_ROSE(WineColour.ROSÉ),
    TEMPRANILLO(WineColour.RED),
    TEMPRANILLO_ROSE(WineColour.ROSÉ),
    TREBBIANO(WineColour.WHITE),
    UGNI_BLANC(WineColour.WHITE),
    VIOGNIER(WineColour.WHITE),
    ZINFANDEL(WineColour.RED),
    ZINFANDEL_ROSE(WineColour.ROSÉ),
    NULL(WineColour.NULL);


    public WineColour aColour;

    private WineVariety(WineColour pColour){
        aColour = pColour;
    }

    public enum WineColour {
        RED, WHITE, ROSÉ, GRAY, ORANGE, PORT, SPARKLING, NULL;
    }
}
