package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.CommentsValidator;

public class CommentsParser extends ParamsParser<String> {
    private static CommentsParser INSTANCE = new CommentsParser();

    public static CommentsParser getInstance() {
        return INSTANCE;
    }

    private CommentsParser() {
        super(CommentsValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input == null || input.isEmpty() ? null : input.strip();
    }
}
