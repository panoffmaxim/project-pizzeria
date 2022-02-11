package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class HouseValidatorUser implements Validator<String> {
    public static final int MAX_LENGTH = 10;
    private static HouseValidatorUser INSTANCE = new HouseValidatorUser();

    public static HouseValidatorUser getInstance() {
        return INSTANCE;
    }

    private HouseValidatorUser() {
    }

    @Override
    public boolean isValid(String input) {
        return (input == null) || (!input.isEmpty() && input.length() < MAX_LENGTH);
    }
}
