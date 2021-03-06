package kz.epam.pizzeria.service.helper.impl;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.helper.PutItemService;

import java.util.Map;
import java.util.Optional;

public class PutItemServiceImpl implements PutItemService {
    private static PutItemServiceImpl INSTANCE = new PutItemServiceImpl();

    public static PutItemServiceImpl getInstance() {
        return INSTANCE;
    }

    private PutItemServiceImpl() {
    }

    @Override
    public void putProduct(Product product, Map<Product, Integer> products) {
        Optional<Product> any = products.keySet().stream()
                .filter(s -> s.getId().equals(product.getId()))
                .findAny();
        if (any.isPresent()) {
            Product prod = any.get();
            products.put(prod, products.get(prod) + 1);
        } else {
            products.put(product, 1);
        }
    }
}
