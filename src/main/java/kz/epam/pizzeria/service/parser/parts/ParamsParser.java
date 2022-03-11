package kz.epam.pizzeria.service.parser.parts;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.service.validator.Validator;

public abstract class ParamsParser<T> {
    private static final Logger LOGGER = LogManager.getLogger(ParamsParser.class);
    private Validator<T> validator;
    public ParamsParser(Validator<T> validator) {
        this.validator = validator;
    }

    protected abstract T modify(String input) throws Exception;

    public OptionalNullable<T> parse(String input) {
        LOGGER.debug("validator = {}", validator);
        try {
            T modify = modify(input);
            if (validator.isValid(modify)) {
                return OptionalNullable.of(modify);
            }
            return OptionalNullable.empty();
        } catch (Exception e) {
            LOGGER.debug("validator exception ", e);
            return OptionalNullable.empty();
        }
    }
}
