package kz.epam.pizzeria.service.parser.parts.impl;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static org.testng.Assert.*;

public class HouseParserOrderTest {
    private final HouseParserOrder houseParserOrder = HouseParserOrder.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {"Some text", true, "Some text"},
                {"", false, null},
                {null, false, null},
                {"any text", true, "any text"},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, String endValue) {
        if (result) {
            assertEquals(houseParserOrder.parse(input).get(), endValue);
        } else {
            assertFalse(houseParserOrder.parse(input).isPresent());
        }
    }
}
