package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.PriceValidator;

public class PriceParser extends ParamsParser<Integer> {
    private static PriceParser INSTANCE = new PriceParser();

    public static PriceParser getInstance() {
        return INSTANCE;
    }

    private PriceParser() {
        super(PriceValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
