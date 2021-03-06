package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class UsernameValidator implements Validator<String> {
    private static UsernameValidator INSTANCE = new UsernameValidator();
    private static final String USERNAME_REGEX = "[\\p{javaAlphabetic}\\d]{1,20}";

    public static UsernameValidator getInstance() {
        return INSTANCE;
    }

    private UsernameValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return Pattern.compile(USERNAME_REGEX, Pattern.UNICODE_CASE).matcher(input).matches();
    }
}
