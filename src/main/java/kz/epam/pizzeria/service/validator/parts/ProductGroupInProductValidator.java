package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

public class ProductGroupInProductValidator implements Validator<Integer> {
    private static ProductGroupInProductValidator INSTANCE = new ProductGroupInProductValidator();
    private static final int MIN_VALUE = 0;

    public static ProductGroupInProductValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupInProductValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input == null || input > MIN_VALUE;
    }
}
