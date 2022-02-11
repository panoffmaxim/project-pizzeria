package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class HouseValidatorOrder implements Validator<String> {
    public static final int MAX_LENGTH = 10;
    private static HouseValidatorOrder INSTANCE = new HouseValidatorOrder();

    public static HouseValidatorOrder getInstance() {
        return INSTANCE;
    }

    private HouseValidatorOrder() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty() && input.length()< MAX_LENGTH;
    }
}
