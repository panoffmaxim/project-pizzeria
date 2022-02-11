package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.User;

/**
 * Dedicated to validate {@link User#getUsername()} and {@link User#getPassword()} before searching in the database
 */
public class LoginValidator {
    private static LoginValidator INSTANCE = new LoginValidator();

    public static LoginValidator getInstance() {
        return INSTANCE;
    }

    private LoginValidator() {
    }
    public boolean isValid(String username, String password) {
        return true;
    }
}
