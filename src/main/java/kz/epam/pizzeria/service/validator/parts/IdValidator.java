package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class IdValidator implements Validator<Integer> {
    private static IdValidator INSTANCE = new IdValidator();

    public static IdValidator getInstance() {
        return INSTANCE;
    }

    private IdValidator() {
    }
    private static final int MIN_EXCLUDE_VALUE = 0;

    @Override
    public boolean isValid(Integer input) {
        return input != null && input > MIN_EXCLUDE_VALUE;
    }
}
