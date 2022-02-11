package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class BooleanParamValidator implements Validator<Boolean> {
    private static BooleanParamValidator INSTANCE = new BooleanParamValidator();

    public static BooleanParamValidator getInstance() {
        return INSTANCE;
    }

    private BooleanParamValidator() {
    }

    @Override
    public boolean isValid(Boolean input) {
        return input != null;
    }
}
