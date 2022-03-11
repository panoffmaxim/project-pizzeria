package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class StreetValidatorUser implements Validator<String> {
    private static StreetValidatorUser INSTANCE = new StreetValidatorUser();

    public static StreetValidatorUser getInstance() {
        return INSTANCE;
    }

    private StreetValidatorUser() {
    }

    @Override
    public boolean isValid(String input) {
        return input == null || (!input.isEmpty() && input.length() < ValidatorConstants.STREET_NAME_MAX_LENGTH);
    }
}
