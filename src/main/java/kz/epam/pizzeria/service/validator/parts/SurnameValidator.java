package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class SurnameValidator implements Validator<String> {
    private static SurnameValidator INSTANCE = new SurnameValidator();
    public static final String SURNAME_REGEX = "[\\p{javaAlphabetic}-]{1,20}";

    public static SurnameValidator getInstance() {
        return INSTANCE;
    }

    private SurnameValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return input == null || Pattern.compile(SURNAME_REGEX, Pattern.UNICODE_CASE).matcher(input).matches();
    }
}
