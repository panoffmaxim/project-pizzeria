package kz.epam.pizzeria.service.exception;

/**
 * Indicates exceptions connected to pagination {@link kz.epam.pizzeria.service.pagination}
 */
public class PaginationException extends ServiceException {
    public PaginationException() {
    }

    public PaginationException(String message) {
        super(message);
    }

    public PaginationException(String message, Throwable cause) {
        super(message, cause);
    }

    public PaginationException(Throwable cause) {
        super(cause);
    }
}
