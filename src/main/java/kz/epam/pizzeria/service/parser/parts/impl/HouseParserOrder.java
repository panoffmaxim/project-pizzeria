package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.HouseValidatorOrder;

public class HouseParserOrder extends ParamsParser<String> {
    private static HouseParserOrder INSTANCE = new HouseParserOrder();

    public static HouseParserOrder getInstance() {
        return INSTANCE;
    }

    private HouseParserOrder() {
        super(HouseValidatorOrder.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
