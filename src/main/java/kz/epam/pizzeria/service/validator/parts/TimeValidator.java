package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

import java.time.LocalDateTime;

public class TimeValidator implements Validator<LocalDateTime> {
    private static TimeValidator INSTANCE = new TimeValidator();

    public static TimeValidator getInstance() {
        return INSTANCE;
    }

    private TimeValidator() {
    }

    @Override
    public boolean isValid(LocalDateTime input) {
        return input != null &&
                input.compareTo(LocalDateTime.now().plus(ValidatorConstants.MIN_DIFFERENCE)) > 0 &&
                input.compareTo(LocalDateTime.now().plus(ValidatorConstants.MAX_DIFFERENCE)) < 0;
    }
}
