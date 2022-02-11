package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link User#getName()}
 */
public class NameValidator implements Validator<String> {
    private static NameValidator INSTANCE = new NameValidator();

    public static NameValidator getInstance() {
        return INSTANCE;
    }

    private NameValidator() {
    }
    //language=RegExp
    public static final String NAME_REGEX = "^[\\p{javaAlphabetic}]+(([',. \\-][\\p{javaAlphabetic} ])?[\\p{javaAlphabetic}]*)*$";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return COMPILE.matcher(input).matches();
    }
}
