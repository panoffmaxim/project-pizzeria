package kz.epam.pizzeria.service.exception;

public class IllegalPathParamException extends ServiceException {
    public IllegalPathParamException(String message) {
        super(message);
    }

    public IllegalPathParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
