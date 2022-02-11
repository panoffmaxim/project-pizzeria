package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.HouseValidatorUser;

public class HouseParserUser extends ParamsParser<String> {
    private static HouseParserUser INSTANCE = new HouseParserUser();

    public static HouseParserUser getInstance() {
        return INSTANCE;
    }

    private HouseParserUser() {
        super(HouseValidatorUser.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
