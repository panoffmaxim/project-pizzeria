package kz.epam.pizzeria.service.pagination;

import kz.epam.pizzeria.service.exception.PaginationException;

public interface PaginationCalculator {
    int calculatePartParam(String pagination) throws PaginationException;
}
