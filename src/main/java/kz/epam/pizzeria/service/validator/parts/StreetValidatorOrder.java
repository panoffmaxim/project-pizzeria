package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class StreetValidatorOrder implements Validator<String> {
    private static StreetValidatorOrder INSTANCE = new StreetValidatorOrder();

    public static StreetValidatorOrder getInstance() {
        return INSTANCE;
    }

    private StreetValidatorOrder() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && !input.isEmpty() && input.length() < ValidatorConstants.STREET_NAME_MAX_LENGTH;
    }
}
