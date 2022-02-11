package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.OrderStatusForOperatorValidator;

/**
 * Dedicated to parse String to {@link Order#getStatus()} value
 * with operator calling
 */
public class OrderStatusForOperatorParser extends ParamsParser<OrderStatus> {
    private static OrderStatusForOperatorParser INSTANCE = new OrderStatusForOperatorParser();

    public static OrderStatusForOperatorParser getInstance() {
        return INSTANCE;
    }

    private OrderStatusForOperatorParser() {
        super(OrderStatusForOperatorValidator.getInstance());
    }

    @Override
    protected OrderStatus modify(String input) throws Exception {
        return OrderStatus.valueOf(input);
    }
}
