package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.EmailValidator;

/**
 * Dedicated to parse String to {@link DeliveryInf#getEmail()}
 */
public class EmailParser extends ParamsParser<String> {
    private static EmailParser INSTANCE = new EmailParser();

    public static EmailParser getInstance() {
        return INSTANCE;
    }

    private EmailParser() {
        super(EmailValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input.isEmpty() ? null : input.strip();
    }
}
