package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.HouseValidatorOrder;

/**
 * Dedicated to parse String to house {@link DeliveryInf#getHouse()} value
 */
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
