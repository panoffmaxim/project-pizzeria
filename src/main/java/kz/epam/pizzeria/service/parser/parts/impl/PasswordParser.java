package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.PasswordValidator;

public class PasswordParser extends ParamsParser<String> {
    private static PasswordParser INSTANCE = new PasswordParser();

    public static PasswordParser getInstance() {
        return INSTANCE;
    }

    private PasswordParser() {
        super(PasswordValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
