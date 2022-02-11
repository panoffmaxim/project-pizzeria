package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link User#getSurname()}
 */
public class SurnameValidator implements Validator<String> {
    private static SurnameValidator INSTANCE = new SurnameValidator();

    public static SurnameValidator getInstance() {
        return INSTANCE;
    }

    private SurnameValidator() {
    }
    //language=RegExp
    public static final String SURNAME_REGEX = "[\\p{javaAlphabetic}-]{1,20}";

    @Override
    public boolean isValid(String input) {
        return input == null || Pattern.compile(SURNAME_REGEX, Pattern.UNICODE_CASE).matcher(input).matches();
    }
}
