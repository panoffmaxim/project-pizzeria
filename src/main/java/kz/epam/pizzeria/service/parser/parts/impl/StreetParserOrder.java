package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.StreetValidatorOrder;

/**
 * Dedicated to parse String to {@link DeliveryInf#getStreet()} value
 */
public class StreetParserOrder extends ParamsParser<String> {
    private static StreetParserOrder INSTANCE = new StreetParserOrder();

    public static StreetParserOrder getInstance() {
        return INSTANCE;
    }

    private StreetParserOrder() {
        super(StreetValidatorOrder.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
