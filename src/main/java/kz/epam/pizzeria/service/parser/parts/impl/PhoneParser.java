package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.PhoneValidator;

public class PhoneParser extends ParamsParser<String> {
    private static PhoneParser INSTANCE = new PhoneParser();

    public static PhoneParser getInstance() {
        return INSTANCE;
    }

    private PhoneParser() {
        super(PhoneValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.replaceAll("\\s", "");
    }
}
