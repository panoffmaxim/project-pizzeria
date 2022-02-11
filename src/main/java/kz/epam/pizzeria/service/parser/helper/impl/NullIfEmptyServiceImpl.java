package kz.epam.pizzeria.service.parser.helper.impl;

import kz.epam.pizzeria.service.parser.helper.NullIfEmptyService;

public class NullIfEmptyServiceImpl implements NullIfEmptyService {
    private static NullIfEmptyServiceImpl INSTANCE = new NullIfEmptyServiceImpl();
    public static NullIfEmptyServiceImpl getInstance() {
        return INSTANCE;
    }

    private NullIfEmptyServiceImpl() {
    }

    @Override
    public String nullIfEmptyString(String param) {
        return param == null || param.isEmpty() ? null : param;
    }

    @Override
    public Integer nullIfEmptyInteger(String param) {
        return param == null || param.isEmpty() ? null : Integer.valueOf(param);
    }
}
