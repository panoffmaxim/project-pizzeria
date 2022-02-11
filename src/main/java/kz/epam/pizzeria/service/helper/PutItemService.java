package kz.epam.pizzeria.service.helper;

import kz.epam.pizzeria.entity.db.impl.Product;

import java.util.Map;

public interface PutItemService {
    void putProduct(final Product product, final Map<Product, Integer> products);
}
