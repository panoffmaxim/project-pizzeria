package kz.epam.pizzeria.command;

import java.util.Locale;

public enum Languages {
    RU("ru", new Locale("ru", "RU")),
    EN("en", new Locale("en", "US"));

    private String key;
    private Locale locale;

    Languages(String key, Locale locale) {
        this.key = key;
        this.locale = locale;
    }

    public String getKey() {
        return key;
    }

    public Locale getLocale() {
        return locale;
    }
}
