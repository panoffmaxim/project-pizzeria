package kz.epam.pizzeria.service.pagination.impl;

import kz.epam.pizzeria.service.exception.PaginationException;
import kz.epam.pizzeria.service.pagination.PaginationCalculator;

public class PaginationCalculatorImpl implements PaginationCalculator {

    @Override
    public int calculatePartParam(String pagination) throws PaginationException {
        if (pagination == null) {
            throw new PaginationException("Param is null");
        }
        try {
            int result = Integer.parseInt(pagination);
            if (result < 1) {
                throw new PaginationException("Param less than 1, param = " + result);
            }
            return result;
        } catch (NumberFormatException e) {
            throw new PaginationException("Can't parse param to int, param = " + pagination, e);
        }
    }
}
