package kz.epam.pizzeria.service.exception;

public class PaginationException extends ServiceException {
    public PaginationException(String message) {
        super(message);
    }

    public PaginationException(String message, Throwable cause) {
        super(message, cause);
    }
}
