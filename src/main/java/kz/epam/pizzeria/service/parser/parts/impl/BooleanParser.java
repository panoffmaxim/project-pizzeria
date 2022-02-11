package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.BooleanParamValidator;

public class BooleanParser extends ParamsParser<Boolean> {
    private static BooleanParser INSTANCE = new BooleanParser();

    public static BooleanParser getInstance() {
        return INSTANCE;
    }

    private static final String TRUE = "1";

    private BooleanParser() {
        super(BooleanParamValidator.getInstance());
    }

    @Override
    protected Boolean modify(String input) throws Exception {
        return TRUE.equals(input);
    }
}
