package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.service.validator.Validator;

public class OrderStatusForOperatorValidator implements Validator<OrderStatus> {
    private static OrderStatusForOperatorValidator INSTANCE = new OrderStatusForOperatorValidator();

    public static OrderStatusForOperatorValidator getInstance() {
        return INSTANCE;
    }

    private OrderStatusForOperatorValidator() {
    }

    @Override
    public boolean isValid(OrderStatus input) {
        return input != null && input != OrderStatus.WAITING;
    }
}
