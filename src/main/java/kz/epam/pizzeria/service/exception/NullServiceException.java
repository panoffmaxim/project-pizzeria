package kz.epam.pizzeria.service.exception;

public class NullServiceException extends ServiceException {
    public NullServiceException() {
    }

    public NullServiceException(String message) {
        super(message);
    }

    public NullServiceException(Throwable cause) {
        super(cause);
    }
}
