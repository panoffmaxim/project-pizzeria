package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

/**
 * Dedicated to validate room
 */
public class RoomValidator implements Validator<String> {
    private static RoomValidator INSTANCE = new RoomValidator();

    public static RoomValidator getInstance() {
        return INSTANCE;
    }

    private RoomValidator() {
    }
    @Override
    public boolean isValid(String input) {
        return true;
    }
}
