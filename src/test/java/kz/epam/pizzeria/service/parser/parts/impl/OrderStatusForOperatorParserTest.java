package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.enums.OrderStatus;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import static kz.epam.pizzeria.entity.enums.OrderStatus.*;
import static org.testng.Assert.*;

public class OrderStatusForOperatorParserTest {
    private final OrderStatusForOperatorParser orderStatusForOperatorParser =
            OrderStatusForOperatorParser.getInstance();

    @DataProvider(name = "check")
    public Object[][] checkProvider
            () {
        return new Object[][]{
                {null, false,null},
                {WAITING.name(), false, null},
                {CONFIRMED.name(), true, CONFIRMED},
                {CANCELED.name(), true, CANCELED},
                {DELIVERED.name(), true, DELIVERED},
                {DELIVERING.name(), true, DELIVERING},
                {"Some value", false, null},
                {"waiting", false, null},
                {"delivering", false, null},
                {"что-то такое", false, null},
        };
    }

    @Test(description = "Test for BooleanParser",
            dataProvider = "check")
    public void checkInput(String input, Boolean result, OrderStatus endValue) {
        if (result) {
            assertEquals(orderStatusForOperatorParser.parse(input).get(), endValue);
        } else {
            assertFalse(orderStatusForOperatorParser.parse(input).isPresent());
        }
    }
}
