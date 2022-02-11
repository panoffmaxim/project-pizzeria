package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class ProductGroupNameValidator implements Validator<String> {
    private static ProductGroupNameValidator INSTANCE = new ProductGroupNameValidator();
    private static final String NAME_REGEX = "[\\p{javaAlphabetic}\\s\\d-]{1,30}";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    public static ProductGroupNameValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupNameValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
