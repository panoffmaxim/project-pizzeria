package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class HouseValidatorOrder implements Validator<String> {
    private static HouseValidatorOrder INSTANCE = new HouseValidatorOrder();

    public static HouseValidatorOrder getInstance() {
        return INSTANCE;
    }

    private HouseValidatorOrder() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty() && input.length() < ValidatorConstants.HOUSE_NUMBER_MAX_LENGTH;
    }
}
