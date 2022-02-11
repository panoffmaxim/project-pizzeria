package kz.epam.pizzeria.service.pagination;

import kz.epam.pizzeria.service.exception.PaginationException;

/**
 * Dedicated to parse String to int for pagination purpose
 */
public interface PaginationCalculator {
    /**
     * @param pagination String input to parse
     * @return int value of input if it can be parsed
     * @throws PaginationException if pagination is null or can't be parsed to int
     */
    int calculatePartParam(String pagination) throws PaginationException;
}
