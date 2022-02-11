package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class PriceValidator implements Validator<Integer> {
    private static PriceValidator INSTANCE = new PriceValidator();
    private static final int MAX_VALUE = 1_000_000_000;
    private static final int MIN_VALUE = 0;

    public static PriceValidator getInstance() {
        return INSTANCE;
    }

    private PriceValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input >= MIN_VALUE && input <= MAX_VALUE;
    }
}
