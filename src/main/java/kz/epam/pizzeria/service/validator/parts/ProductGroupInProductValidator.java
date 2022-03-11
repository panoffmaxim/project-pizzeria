package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.service.validator.Validator;

public class ProductGroupInProductValidator implements Validator<Integer> {
    private static ProductGroupInProductValidator INSTANCE = new ProductGroupInProductValidator();

    public static ProductGroupInProductValidator getInstance() {
        return INSTANCE;
    }

    private ProductGroupInProductValidator() {
    }

    @Override
    public boolean isValid(Integer input) {
        return input == null || input > ValidatorConstants.PRODUCT_GROUP_MIN_VALUE;
    }
}
