package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.ProductGroupInProductValidator;

/**
 * Dedicated to parse String to {@link Product#getProductGroup()} id value
 */
public class ProductGroupInProductParser extends ParamsParser<Integer> {
    private static ProductGroupInProductParser INSTANCE = new ProductGroupInProductParser();

    public static ProductGroupInProductParser getInstance() {
        return INSTANCE;
    }

    private ProductGroupInProductParser() {
        super(ProductGroupInProductValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : Integer.valueOf(input);
    }
}
