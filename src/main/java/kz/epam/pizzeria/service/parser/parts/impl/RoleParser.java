package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.enums.Role;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.RoleValidator;

public class RoleParser extends ParamsParser<Role> {
    private static RoleParser INSTANCE = new RoleParser();

    public static RoleParser getInstance() {
        return INSTANCE;
    }

    private RoleParser() {
        super(RoleValidator.getInstance());
    }

    @Override
    protected Role modify(String input) throws Exception {
        return Role.valueOf(input);
    }
}
