package kz.epam.pizzeria.service.parser.helper;

import kz.epam.pizzeria.service.exception.IllegalPathParamException;

public interface PathVarCalculator {
    String DELIMITER = "/";

    Integer findLastInteger(String path) throws IllegalPathParamException;
}
