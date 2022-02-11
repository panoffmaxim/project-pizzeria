package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class StreetValidatorOrder implements Validator<String> {
    public static final int MAX_LENGTH = 50;
    private static StreetValidatorOrder INSTANCE = new StreetValidatorOrder();

    public static StreetValidatorOrder getInstance() {
        return INSTANCE;
    }

    private StreetValidatorOrder() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty() && input.length() < MAX_LENGTH;
    }
}
