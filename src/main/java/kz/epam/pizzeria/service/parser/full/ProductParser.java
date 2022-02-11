package kz.epam.pizzeria.service.parser.full;

import kz.epam.pizzeria.entity.db.impl.Product;

import java.util.Map;

public interface ProductParser {
    Product parseProduct(Map<String, String> redirect, String productGroup, String price, String weight);

    Product parseProductWithId(Map<String, String> redirect, String id, String productGroup, String price, String weight);
}
