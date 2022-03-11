package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class HouseValidatorUser implements Validator<String> {
    private static HouseValidatorUser INSTANCE = new HouseValidatorUser();

    public static HouseValidatorUser getInstance() {
        return INSTANCE;
    }

    private HouseValidatorUser() {
    }

    @Override
    public boolean isValid(String input) {
        return (input == null) || (!input.isEmpty() && input.length() < ValidatorConstants.HOUSE_NUMBER_MAX_LENGTH);
    }
}
