package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.FloorValidator;

/**
 * Dedicated to parse String to floor value
 */
public class FloorParser extends ParamsParser<Integer> {
    private static FloorParser INSTANCE = new FloorParser();

    public static FloorParser getInstance() {
        return INSTANCE;
    }

    private FloorParser() {
        super(FloorValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return input.isEmpty() ? null : Integer.valueOf(input);
    }
}
