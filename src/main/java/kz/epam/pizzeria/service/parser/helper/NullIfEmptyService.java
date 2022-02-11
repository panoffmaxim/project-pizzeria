package kz.epam.pizzeria.service.parser.helper;

/**
 * Dedicated to return null if input is empty for different types
 */
public interface NullIfEmptyService {

    String nullIfEmptyString(String param);

    Integer nullIfEmptyInteger(String param);
}
