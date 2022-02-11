package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.RoomValidator;

/**
 * Dedicated to parse String to room value
 */
public class RoomParser extends ParamsParser<String> {
    private static RoomParser INSTANCE = new RoomParser();

    public static RoomParser getInstance() {
        return INSTANCE;
    }

    private RoomParser() {
        super(RoomValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
