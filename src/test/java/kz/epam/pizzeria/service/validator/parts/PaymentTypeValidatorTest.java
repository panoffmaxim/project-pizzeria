package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.entity.enums.PaymentType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PaymentTypeValidatorTest {
    private final PaymentTypeValidator paymentTypeValidator = PaymentTypeValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {PaymentType.CASH, true},
                {PaymentType.BANK_CARD, true},
        };
    }

    @Test(description = "Test for PaymentTypeValidator",
            dataProvider = "check")
    public void checkInput(PaymentType input, Boolean result) {
        assertEquals(paymentTypeValidator.isValid(input), (boolean) result);
    }
}
