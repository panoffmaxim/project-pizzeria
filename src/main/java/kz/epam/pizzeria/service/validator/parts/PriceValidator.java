package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class PriceValidator implements Validator<Integer> {
    private static PriceValidator INSTANCE = new PriceValidator();

    public static PriceValidator getInstance() {
        return INSTANCE;
    }

    private PriceValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input >= ValidatorConstants.PRICE_MIN_VALUE && input <= ValidatorConstants.PRICE_MAX_VALUE;
    }
}
