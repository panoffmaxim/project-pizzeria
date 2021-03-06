package kz.epam.pizzeria.service.helper.impl;

import kz.epam.pizzeria.entity.db.impl.Product;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static org.testng.Assert.*;

public class PutItemServiceImplTest {
    private final PutItemServiceImpl putItemServiceImpl = PutItemServiceImpl.getInstance();

    @Test
    public void testPutProductThrowsException() {
        Map<Product, Integer> basket = new HashMap<>();
        basket.put(Product.newBuilder().id(1).build(), 1);
        assertThrows(
                NullPointerException.class,
                () -> putItemServiceImpl.putProduct(null, basket)
        );
    }

    @Test
    public void testPutProductThrowsExceptionBasket() {
        assertThrows(
                NullPointerException.class,
                () -> putItemServiceImpl.putProduct(Product.newBuilder().id(1).build(), null)
        );
    }

    @Test
    public void testPositive() {
        Map<Product, Integer> basket = new HashMap<>();
        basket.put(Product.newBuilder().id(1).build(), 1);
        putItemServiceImpl.putProduct(Product.newBuilder().id(1).build(), basket);
        assertEquals(basket.get(Product.newBuilder().id(1).build()), Integer.valueOf(2));
    }
}
