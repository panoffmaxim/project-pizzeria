package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class PorchValidator implements Validator<Integer> {
    private static PorchValidator INSTANCE = new PorchValidator();

    public static PorchValidator getInstance() {
        return INSTANCE;
    }

    private PorchValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input == null || (input > ValidatorConstants.PORCH_MIN_VALUE && input < ValidatorConstants.PORCH_MAX_VALUE);
    }
}
