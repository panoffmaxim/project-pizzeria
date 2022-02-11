package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.UsernameValidator;

/**
 * Dedicated to parse String to {@link User#getUsername()} value
 */
public class UsernameParser extends ParamsParser<String> {
    private static UsernameParser INSTANCE = new UsernameParser();

    public static UsernameParser getInstance() {
        return INSTANCE;
    }

    private UsernameParser() {
        super(UsernameValidator.getInstance());
    }

    @Override
    protected String modify(String input) {
        return (input == null || input.isEmpty()) ? null : input.strip();
    }
}
