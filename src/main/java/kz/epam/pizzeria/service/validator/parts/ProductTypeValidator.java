package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.service.validator.Validator;

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
