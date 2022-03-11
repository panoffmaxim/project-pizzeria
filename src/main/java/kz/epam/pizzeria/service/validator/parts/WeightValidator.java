package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class WeightValidator implements Validator<Integer> {
    private static WeightValidator INSTANCE = new WeightValidator();

    public static WeightValidator getInstance() {
        return INSTANCE;
    }

    private WeightValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input > ValidatorConstants.WEIGHT_MIN && input <= ValidatorConstants.WEIGHT_MAX;
    }
}
