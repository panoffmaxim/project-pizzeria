package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.validator.Validator;

/**
 * Dedicated to validate {@link Product#getProductGroup()} id
 */
public class ProductGroupInProductValidator implements Validator<Integer> {
    private static ProductGroupInProductValidator INSTANCE = new ProductGroupInProductValidator();

    public static ProductGroupInProductValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupInProductValidator() {
    }

    private static final int MIN_VALUE = 0;

    @Override
    public boolean isValid(Integer input) {
        return input == null || input > MIN_VALUE;
    }
}
