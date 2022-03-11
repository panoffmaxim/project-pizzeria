package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class IdValidator implements Validator<Integer> {
    private static IdValidator INSTANCE = new IdValidator();

    public static IdValidator getInstance() {
        return INSTANCE;
    }

    private IdValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input != null && input > ValidatorConstants.ID_MIN_EXCLUDE_VALUE;
    }
}
