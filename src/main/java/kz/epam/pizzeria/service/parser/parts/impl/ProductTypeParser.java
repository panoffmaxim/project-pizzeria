package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.ProductTypeValidator;

/**
 * Dedicated to parse String to {@link ProductType} value
 */
public class ProductTypeParser extends ParamsParser<ProductType> {
    private static ProductTypeParser INSTANCE = new ProductTypeParser();

    public static ProductTypeParser getInstance() {
        return INSTANCE;
    }

    private ProductTypeParser() {
        super(ProductTypeValidator.getInstance());
    }

    @Override
    protected ProductType modify(String input) throws Exception {
        return ProductType.valueOf(input);
    }
}
