package main;

import java.util.ArrayList;
import java.util.Locale;

public enum Countries {
    EN("en"),
    FR("fr");

    public String aLanguage;
    public final ArrayList<String> aCountries = new ArrayList<>();

    private Countries(String pLanguage) {
        Locale locale = new Locale(pLanguage);
        aLanguage = pLanguage;
        for (String countryCode : Locale.getISOCountries()) {
            Locale obj = new Locale("", countryCode);
            aCountries.add(obj.getDisplayCountry());
        }
    }

}
