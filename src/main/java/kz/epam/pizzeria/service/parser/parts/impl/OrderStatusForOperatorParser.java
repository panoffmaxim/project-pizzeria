package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.OrderStatusForOperatorValidator;

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
