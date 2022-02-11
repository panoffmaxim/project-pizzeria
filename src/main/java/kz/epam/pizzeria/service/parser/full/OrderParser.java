package kz.epam.pizzeria.service.parser.full;

import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.Product;

import java.util.Map;

public interface OrderParser {
    Order parse(Map<String, String> redirect, String streetParam, String commentsParam, String floorParam, String porchParam,
                String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam, String timeParam,
                Map<Product, Integer> basket, String paymentTypeParam);

    boolean parseWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam, String floorParam,
                          String porchParam, String roomParam, String houseParam, String nameParam, String phoneParam, String emailParam,
                          String timeParam, String paymentTypeParam);

    boolean parseForOperatorWithBase(Map<String, String> redirect, Order order, String streetParam, String commentsParam,
                                     String floorParam, String porchParam, String roomParam, String houseParam, String nameParam,
                                     String phoneParam, String emailParam, String timeParam, String statusParam, String paymentTypeParam,
                                     String priceParam);
}
