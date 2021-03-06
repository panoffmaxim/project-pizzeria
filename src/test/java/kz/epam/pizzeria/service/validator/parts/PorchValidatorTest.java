package kz.epam.pizzeria.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class PorchValidatorTest {
    private final PorchValidator porchValidator = PorchValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {1, true},
                {25, true},
                {49, true},
                {50, false},
                {0, false},
                {100, false},
                {10000000, false},
                {-1, false},
                {-100, false},
                {-10000000, false},
                {null, true},
        };
    }

    @Test(description = "Test for PorchValidator",
            dataProvider = "check")
    public void checkInput(Integer input, Boolean result) {
        assertEquals(porchValidator.isValid(input), (boolean) result);
    }
}
