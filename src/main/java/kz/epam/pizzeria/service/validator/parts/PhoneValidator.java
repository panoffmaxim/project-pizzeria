package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate phone
 */
public class PhoneValidator implements Validator<String> {
    private static PhoneValidator INSTANCE = new PhoneValidator();

    public static PhoneValidator getInstance() {
        return INSTANCE;
    }

    private PhoneValidator() {
    }

    public static final String PHONE_REGEX = "\\d{11}";
    private static final Pattern COMPILE = Pattern.compile(PHONE_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return COMPILE.matcher(input).matches();
    }
}
