package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.ProductGroupDescriptionValidator;

public class ProductGroupDescriptionParser extends ParamsParser<String> {
    private static ProductGroupDescriptionParser INSTANCE = new ProductGroupDescriptionParser();

    public static ProductGroupDescriptionParser getInstance() {
        return INSTANCE;
    }

    private ProductGroupDescriptionParser() {
        super(ProductGroupDescriptionValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.strip();
    }
}
