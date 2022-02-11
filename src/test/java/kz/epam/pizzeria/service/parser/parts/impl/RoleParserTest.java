package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.enums.Role;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static kz.epam.pizzeria.entity.enums.Role.*;
import static org.testng.Assert.*;

public class RoleParserTest {
    private final RoleParser roleParser = RoleParser.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false, null},
                {Role.ANON.name(), false, null},
                {Role.CLIENT.name(), true, CLIENT},
                {Role.ADMIN.name(), true, ADMIN},
                {Role.OPERATOR.name(), true, OPERATOR},
                {"Some value", false, null},
                {"waiting", false, null},
                {"delivering", false, null},
                {"что-то такое", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, Role endValue) {
        if (result) {
            assertEquals(roleParser.parse(input).get(), endValue);
        } else {
            assertFalse(roleParser.parse(input).isPresent());
        }
    }
}
