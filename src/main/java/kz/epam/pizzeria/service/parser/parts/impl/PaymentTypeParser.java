package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.PaymentTypeValidator;

public class PaymentTypeParser extends ParamsParser<PaymentType> {
    private static PaymentTypeParser INSTANCE = new PaymentTypeParser();

    public static PaymentTypeParser getInstance() {
        return INSTANCE;
    }

    private PaymentTypeParser() {
        super(PaymentTypeValidator.getInstance());
    }

    @Override
    protected PaymentType modify(String input) throws Exception {
        return PaymentType.valueOf(input);
    }
}
