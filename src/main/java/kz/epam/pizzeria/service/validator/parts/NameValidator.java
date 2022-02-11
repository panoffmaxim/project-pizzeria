package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class NameValidator implements Validator<String> {
    private static NameValidator INSTANCE = new NameValidator();
    public static final String NAME_REGEX = "^[\\p{javaAlphabetic}]+(([',. \\-][\\p{javaAlphabetic} ])?[\\p{javaAlphabetic}]*)*$";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    public static NameValidator getInstance() {
        return INSTANCE;
    }

    private NameValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return COMPILE.matcher(input).matches();
    }
}
