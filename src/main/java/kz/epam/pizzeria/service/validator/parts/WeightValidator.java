package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class WeightValidator implements Validator<Integer> {
    private static WeightValidator INSTANCE = new WeightValidator();

    public static WeightValidator getInstance() {
        return INSTANCE;
    }
    private static final int MAX = 1_000_000;
    private static final int MIN = 0;

    private WeightValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input > MIN && input <= MAX;
    }
}
