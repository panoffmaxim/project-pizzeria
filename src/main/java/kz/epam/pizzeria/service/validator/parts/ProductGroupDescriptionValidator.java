package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class ProductGroupDescriptionValidator implements Validator<String> {
    private static ProductGroupDescriptionValidator INSTANCE = new ProductGroupDescriptionValidator();
    private static final String DESCR_REGEX = "[^\n]{1,200}";
    private static final Pattern COMPILE = Pattern.compile(DESCR_REGEX, Pattern.UNICODE_CASE);

    public static ProductGroupDescriptionValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupDescriptionValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
