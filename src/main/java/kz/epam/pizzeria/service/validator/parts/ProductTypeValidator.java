package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.service.validator.Validator;

/**
 * Dedicated to validate {@link ProductType}
 */
public class ProductTypeValidator implements Validator<ProductType> {
    private static ProductTypeValidator INSTANCE = new ProductTypeValidator();

    public static ProductTypeValidator getInstance() {
        return INSTANCE;
    }

    private ProductTypeValidator() {
    }
    @Override
    public boolean isValid(ProductType input) {
        return input != null;
    }
}
