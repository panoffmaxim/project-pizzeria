package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class EmailValidator implements Validator<String> {
    private static EmailValidator INSTANCE = new EmailValidator();

    public static EmailValidator getInstance() {
        return INSTANCE;
    }

    private EmailValidator() {
    }

    private final org.apache.commons.validator.routines.EmailValidator emailValidator =
            org.apache.commons.validator.routines.EmailValidator.getInstance();

    @Override
    public boolean isValid(String input) {
        return emailValidator.isValid(input);
    }
}
