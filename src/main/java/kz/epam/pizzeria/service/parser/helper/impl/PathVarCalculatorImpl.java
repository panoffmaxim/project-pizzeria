package kz.epam.pizzeria.service.parser.helper.impl;

import kz.epam.pizzeria.service.exception.IllegalPathParamException;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

public class PathVarCalculatorImpl implements PathVarCalculator {
    private static PathVarCalculatorImpl INSTANCE = new PathVarCalculatorImpl();

    public static PathVarCalculatorImpl getInstance() {
        return INSTANCE;
    }

    private PathVarCalculatorImpl() {
    }

    @Override
    public Integer findLastInteger(String path) throws IllegalPathParamException {
        if (path == null || !path.contains(DELIMITER)) {
            throw new IllegalPathParamException("Path doesn't contains delimiter character.");
        }
        try {
            String stVar = path.substring(path.lastIndexOf(DELIMITER) + 1);
            return Integer.valueOf(stVar);
        } catch (IndexOutOfBoundsException | NumberFormatException e) {
            throw new IllegalPathParamException("Problem in parsing the string.", e);
        }
    }
}
