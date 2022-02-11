package kz.epam.pizzeria.service.validator.parts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.service.validator.Validator;

import java.time.LocalDateTime;

public class DateTimeValidator implements Validator<LocalDateTime> {
    private static final Logger LOGGER = LogManager.getLogger(DateTimeValidator.class);
    private static DateTimeValidator INSTANCE = new DateTimeValidator();

    public static DateTimeValidator getInstance() {
        return INSTANCE;
    }

    private DateTimeValidator() {
    }

    @Override
    public boolean isValid(LocalDateTime input) {
        LOGGER.debug("isValid: input = {}", input);
        return input != null;
    }
}
