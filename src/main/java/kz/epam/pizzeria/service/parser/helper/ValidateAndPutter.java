package kz.epam.pizzeria.service.parser.helper;

import kz.epam.pizzeria.entity.struct.OptionalNullable;

import java.util.Map;

public interface ValidateAndPutter {
    boolean validateAndPut(Map<String, String> redirect, OptionalNullable<?> optional, String label, String param);
}
