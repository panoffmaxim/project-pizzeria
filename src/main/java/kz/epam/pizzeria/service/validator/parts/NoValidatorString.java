package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class NoValidatorString implements Validator<String> {
    private static NoValidatorString INSTANCE = new NoValidatorString();

    public static NoValidatorString getInstance() {
        return INSTANCE;
    }

    private NoValidatorString() {
    }
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
