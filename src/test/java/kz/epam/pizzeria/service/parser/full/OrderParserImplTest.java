package kz.epam.pizzeria.service.parser.full;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.service.parser.full.impl.OrderParserImpl;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static kz.epam.pizzeria.constant.OtherConstants.POSTFIX;
import static org.testng.Assert.*;

public class OrderParserImplTest {
    private final OrderParser orderParser = new OrderParserImpl();

    @Test
    public void testParse() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "3";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order parse = orderParser.parse(redirect, street, comments, floor, porch, room, house, name, phone, email, time, basket, PaymentType.CASH.name());
        assertNotNull(parse);
    }

    @Test
    public void testParseNegative() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "abc";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order parse = orderParser.parse(redirect, street, comments, floor, porch, room, house, name, phone, email, time, basket, PaymentType.CASH.name());
        assertNull(parse);
    }

    @Test
    public void testParseReturnsParams() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "abc";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);

        orderParser.parse(redirect, street, comments, floor, porch, room, house, name, phone, email, time, basket, PaymentType.CASH.name());
        assertTrue(redirect.containsKey("floor" + POSTFIX));
    }

    @Test
    public void testParseReturnsParamsPositive() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "1";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);

        orderParser.parse(redirect, street, comments, floor, porch, room, house, name, phone, email, time, basket, PaymentType.CASH.name());
        assertTrue(redirect.containsKey("floor"));
    }

    @Test
    public void testParseWithBase() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "3";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();

        boolean parse = orderParser.parseWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, PaymentType.CASH.name());
        assertTrue(parse);
    }

    @Test
    public void testParseWithBaseNegative() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "asd";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);

        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();

        boolean parse = orderParser.parseWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, PaymentType.CASH.name());
        assertFalse(parse);
    }

    @Test
    public void testParseWithBaseErrorContains() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "asd";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();

        orderParser.parseWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, PaymentType.CASH.name());
        assertTrue(redirect.containsKey("floor" + POSTFIX));
    }

    @Test
    public void testParseWithBaseContainsMessage() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "3";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();

        orderParser.parseWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, PaymentType.CASH.name());
        assertTrue(redirect.containsKey("floor"));
    }

    @Test
    public void testParseForOperatorWithBase() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "3";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "2020-10-20T10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();
        String status = OrderStatus.DELIVERED.name();
        String paymentType = PaymentType.BANK_CARD.name();
        String price = "2345";
        boolean parse = orderParser.parseForOperatorWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, status, paymentType, price);

        assertTrue(parse);
    }

    @Test
    public void testParseForOperatorWithBaseNegative() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "asd";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();
        String status = OrderStatus.DELIVERED.name();
        String paymentType = PaymentType.BANK_CARD.name();
        String price = "2345";
        boolean parse = orderParser.parseForOperatorWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, status, paymentType, price);

        assertFalse(parse);

    }

    @Test
    public void testParseForOperatorWithBaseErrorMsg() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "asd";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();
        String status = OrderStatus.DELIVERED.name();
        String paymentType = PaymentType.BANK_CARD.name();
        String price = "2345";
        orderParser.parseForOperatorWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, status, paymentType, price);

        assertTrue(redirect.containsKey("floor" + POSTFIX));
    }

    @Test
    public void testParseForOperatorWithBaseContainsMsg() {
        Map<String, String> redirect = new HashMap<>();
        String street = "Lenina";
        String comments = "Пожалуйста, скорее.";
        String floor = "asd";
        String porch = "4";
        String room = "3";
        String house = "3A";
        String name = "Антон";
        String phone = "111222333";
        String email = "abc@gmail.com";
        String time = "10:15";
        Map<Product, Integer> basket = new HashMap<>();
        Product build = Product.newBuilder().id(1).weight(100).price(1004).build();
        basket.put(build, 3);
        Order order = Order.newBuilder()
                .id(23)
                .deliveryInf(
                        DeliveryInf.newBuilder()
                                .id(22)
                                .build()
                )
                .products(basket)
                .build();
        String status = OrderStatus.DELIVERED.name();
        String paymentType = PaymentType.BANK_CARD.name();
        String price = "2345";
        orderParser.parseForOperatorWithBase(redirect, order, street, comments, floor, porch, room, house, name, phone, email, time, status, paymentType, price);

        assertTrue(redirect.containsKey("floor"));
    }
}
