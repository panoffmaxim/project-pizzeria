package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.SurnameValidator;

public class SurnameParser extends ParamsParser<String> {
    private static SurnameParser INSTANCE = new SurnameParser();

    public static SurnameParser getInstance() {
        return INSTANCE;
    }

    private SurnameParser() {
        super(SurnameValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
