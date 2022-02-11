package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.NoValidatorString;
import kz.epam.pizzeria.service.validator.parts.PasswordValidator;

/**
 * Dedicated to parse String to password value
 */
public class PasswordParser extends ParamsParser<String> {
    private static PasswordParser INSTANCE = new PasswordParser();

    public static PasswordParser getInstance() {
        return INSTANCE;
    }

    private PasswordParser() {
//        super(PasswordValidator.getInstance());
        super(NoValidatorString.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
