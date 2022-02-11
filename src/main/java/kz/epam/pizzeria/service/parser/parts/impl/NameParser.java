package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.NameValidator;

public class NameParser extends ParamsParser<String> {
    private static NameParser INSTANCE = new NameParser();

    public static NameParser getInstance() {
        return INSTANCE;
    }

    private NameParser() {
        super(NameValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
