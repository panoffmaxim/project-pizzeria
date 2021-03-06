package kz.epam.pizzeria.service.validator.parts;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class FloorValidatorTest {
    private final FloorValidator floorValidator = FloorValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {0, true},
                {1, true},
                {-1, true},
                {-99, true},
                {-100, false},
                {99, true},
                {100, false},
                {50, true},
                {-50, true},
                {10000000, false},
                {-10000000, false},
        };
    }

    @Test(description = "Test for FloorValidator",
            dataProvider = "check")
    public void checkInput(Integer input, Boolean result) {
        assertEquals(floorValidator.isValid(input), (boolean) result);
    }
}
