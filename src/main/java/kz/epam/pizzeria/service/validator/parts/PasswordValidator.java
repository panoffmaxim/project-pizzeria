package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class PasswordValidator implements Validator<String> {
    private static PasswordValidator INSTANCE = new PasswordValidator();
    public static final String CAPITAL_REGEX = ".*\\p{javaUpperCase}.*";
    private static final Pattern CAPITAL_PATTERN = Pattern.compile(CAPITAL_REGEX, Pattern.UNICODE_CASE);
    public static final String LOVER_REGEX = ".*\\p{javaLowerCase}.*";
    private static final Pattern LOVER_PATTERN = Pattern.compile(LOVER_REGEX, Pattern.UNICODE_CASE);
    public static final String DIGIT_REGEX = ".*\\d.*";
    private static final Pattern DIGIT_PATTERN = Pattern.compile(DIGIT_REGEX, Pattern.UNICODE_CASE);

    public static PasswordValidator getInstance() {
        return INSTANCE;
    }

    private PasswordValidator() {
    }

    @Override
    public boolean isValid(String password) {
        if (password == null || password.isEmpty() || password.length() < 8) {
            return false;
        }
        return CAPITAL_PATTERN.matcher(password).matches()
                && LOVER_PATTERN.matcher(password).matches()
                && DIGIT_PATTERN.matcher(password).matches();
    }
}
