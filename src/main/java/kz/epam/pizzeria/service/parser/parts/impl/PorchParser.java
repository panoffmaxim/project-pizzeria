package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.PorchValidator;

public class PorchParser extends ParamsParser<Integer> {
    private static PorchParser INSTANCE = new PorchParser();

    public static PorchParser getInstance() {
        return INSTANCE;
    }

    private PorchParser() {
        super(PorchValidator.getInstance());
    }

    @Override
    protected Integer modify(String input) throws Exception {
        return input == null || input.isEmpty() ? null : Integer.valueOf(input);
    }
}
