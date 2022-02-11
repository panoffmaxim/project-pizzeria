package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.service.validator.Validator;

/**
 * Dedicated to validate {@link PaymentType}
 */
public class PaymentTypeValidator implements Validator<PaymentType> {
    private static PaymentTypeValidator INSTANCE = new PaymentTypeValidator();

    public static PaymentTypeValidator getInstance() {
        return INSTANCE;
    }

    private PaymentTypeValidator() {
    }
    @Override
    public boolean isValid(PaymentType input) {
        return input != null;
    }
}
