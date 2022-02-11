package kz.epam.pizzeria.service.email;

import java.util.Locale;
import java.util.TreeMap;

public interface MailSender {
    boolean sendRegistration(String email, String url, Locale locale, TreeMap<String, String> parameters);
}
