package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link ProductGroup#getName()}
 */
public class ProductGroupNameValidator implements Validator<String> {
    private static ProductGroupNameValidator INSTANCE = new ProductGroupNameValidator();

    public static ProductGroupNameValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupNameValidator() {
    }

    private static final String NAME_REGEX = "[\\p{javaAlphabetic}\\s\\d-]{1,30}";
    private static final Pattern COMPILE = Pattern.compile(NAME_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
