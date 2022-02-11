package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.ProductGroupNameValidator;

/**
 * Dedicated to parse String to {@link ProductGroup#getName()} value
 */
public class ProductGroupNameParser extends ParamsParser<String> {
    private static ProductGroupNameParser INSTANCE = new ProductGroupNameParser();

    public static ProductGroupNameParser getInstance() {
        return INSTANCE;
    }

    private ProductGroupNameParser() {
        super(ProductGroupNameValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.strip();
    }
}
