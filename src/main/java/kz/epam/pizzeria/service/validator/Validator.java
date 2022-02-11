package kz.epam.pizzeria.service.validator;

public interface Validator<T> {
    boolean isValid(T input);
}
