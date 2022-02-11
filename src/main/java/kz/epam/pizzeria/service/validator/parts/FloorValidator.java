package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class FloorValidator implements Validator<Integer> {
    private static FloorValidator INSTANCE = new FloorValidator();
    private static final int MIN = -100;
    private static final int MAX = 100;

    public static FloorValidator getInstance() {
        return INSTANCE;
    }

    private FloorValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input < MAX && input > MIN);
    }
}
