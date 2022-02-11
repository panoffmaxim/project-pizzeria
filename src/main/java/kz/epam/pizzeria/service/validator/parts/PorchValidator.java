package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class PorchValidator implements Validator<Integer> {
    private static PorchValidator INSTANCE = new PorchValidator();
    private static final int MIN_VALUE = 0;
    private static final int MAX_VALUE = 50;

    public static PorchValidator getInstance() {
        return INSTANCE;
    }

    private PorchValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input > MIN_VALUE && input < MAX_VALUE);
    }
}
