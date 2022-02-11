package kz.epam.pizzeria.service.parser.helper.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.service.parser.helper.ValidateAndPutter;

import java.util.Map;

public class ValidateAndPutterImpl implements kz.epam.pizzeria.service.parser.helper.ValidateAndPutter {
    private static final Logger LOGGER = LogManager.getLogger(ValidateAndPutterImpl.class);
    public static final String POSTFIX = "_error";
    public static final ValidateAndPutter INSTANCE = new ValidateAndPutterImpl();

    public static ValidateAndPutter getInstance() {
        return INSTANCE;
    }

    private ValidateAndPutterImpl() {
    }

    @Override
    public boolean validateAndPut(Map<String, String> redirect, OptionalNullable<?> optional, String label, String param) {
        redirect.put(label, param);
        if (optional.isEmpty()) {
            redirect.put(label + POSTFIX, "true");
            return false;
        }
        LOGGER.debug("label = {}", label);
        LOGGER.debug("param = {}", param);
        return true;
    }
}
