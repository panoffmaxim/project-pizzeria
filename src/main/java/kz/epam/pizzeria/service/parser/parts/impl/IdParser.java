package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.IdValidator;

public class IdParser extends ParamsParser<Integer> {
    private static IdParser INSTANCE = new IdParser();

    public static IdParser getInstance() {
        return INSTANCE;
    }

    private IdParser() {
        super(IdValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return Integer.valueOf(input);
    }
}
