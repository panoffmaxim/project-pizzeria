package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.entity.enums.ProductType;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class ProductTypeValidatorTest {
    private final ProductTypeValidator productTypeValidator = ProductTypeValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {ProductType.PIZZA, true},
                {ProductType.DESSERT, true},
                {ProductType.DRINK, true},
                {ProductType.SNACK, true},
        };
    }

    @Test(description = "Test for ProductTypeValidator",
            dataProvider = "check")
    public void checkInput(ProductType input, Boolean result) {
        assertEquals(productTypeValidator.isValid(input), (boolean) result);
    }
}
