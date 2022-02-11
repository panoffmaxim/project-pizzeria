package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.WeightValidator;

public class WeightParser extends ParamsParser<Integer> {
    private static WeightParser INSTANCE = new WeightParser();

    public static WeightParser getInstance() {
        return INSTANCE;
    }

    private WeightParser() {
        super(WeightValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
