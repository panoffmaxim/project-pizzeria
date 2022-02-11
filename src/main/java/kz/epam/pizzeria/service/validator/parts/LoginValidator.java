package kz.epam.pizzeria.service.validator.parts;

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
