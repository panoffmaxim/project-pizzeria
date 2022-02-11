package kz.epam.pizzeria.service.exception;

import kz.epam.pizzeria.entity.db.Entity;

/**
 * Indicates when parameter is invalid id
 */
public class IllegalIdException extends ServiceException {
    public IllegalIdException() {
    }

    public IllegalIdException(String message) {
        super(message);
    }

    public IllegalIdException(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalIdException(Throwable cause) {
        super(cause);
    }
}
