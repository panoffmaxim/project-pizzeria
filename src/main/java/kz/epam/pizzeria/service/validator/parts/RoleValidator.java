package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.Role;
import kz.epam.pizzeria.service.validator.Validator;

public class RoleValidator implements Validator<Role> {
    private static RoleValidator INSTANCE = new RoleValidator();

    public static RoleValidator getInstance() {
        return INSTANCE;
    }

    private RoleValidator() {
    }

    @Override
    public boolean isValid(Role input) {
        return input != null && input != Role.ANON;
    }
}
