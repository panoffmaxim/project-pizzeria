package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.enums.Role;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class RoleValidatorTest {
    private final RoleValidator roleValidator = RoleValidator.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false},
                {Role.ANON, false},
                {Role.CLIENT, true},
                {Role.ADMIN, true},
                {Role.OPERATOR, true},
        };
    }

    @Test(description = "Test for RoleValidator",
            dataProvider = "check")
    public void checkInput(Role input, Boolean result) {
        assertEquals(roleValidator.isValid(input), (boolean) result);
    }
}
