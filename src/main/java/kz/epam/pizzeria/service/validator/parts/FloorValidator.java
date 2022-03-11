package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class FloorValidator implements Validator<Integer> {
    private static FloorValidator INSTANCE = new FloorValidator();

    public static FloorValidator getInstance() {
        return INSTANCE;
    }

    private FloorValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input < ValidatorConstants.FLOOR_MAX && input > ValidatorConstants.FLOOR_MIN);
    }
}
