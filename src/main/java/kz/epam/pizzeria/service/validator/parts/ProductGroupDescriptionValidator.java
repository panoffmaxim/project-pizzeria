package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link ProductGroup#getDescription()}
 */
public class ProductGroupDescriptionValidator implements Validator<String> {
    private static ProductGroupDescriptionValidator INSTANCE = new ProductGroupDescriptionValidator();

    public static ProductGroupDescriptionValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupDescriptionValidator() {
    }
    private static final String DESCR_REGEX = "[^\n]{1,200}";
    private static final Pattern COMPILE = Pattern.compile(DESCR_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
