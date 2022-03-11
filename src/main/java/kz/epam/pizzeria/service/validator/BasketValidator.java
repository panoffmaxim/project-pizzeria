package kz.epam.pizzeria.service.validator;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.constant.ValidatorConstants;
import kz.epam.pizzeria.entity.db.impl.Product;

import java.util.Map;

public class BasketValidator {
    private static BasketValidator INSTANCE = new BasketValidator();

    public static BasketValidator getInstance() {
        return INSTANCE;
    }

    private BasketValidator() {
    }

    public boolean isValid(Map<Product, Integer> basket, Map<String, String> redirect, String label) {
        if (basket == null || basket.isEmpty()) {
            wrongCallBack(redirect, label);
            return false;
        }
        boolean match = basket.entrySet().stream()
                .anyMatch(e -> {
                    Product key = e.getKey();
                    Integer value = e.getValue();
                    return key == null || key.getId() == null || value == null || value < ValidatorConstants.BASKET_MIN_VALUE;
                });
        if (match) {
            wrongCallBack(redirect, label);
            return false;
        }
        return true;
    }

    private void wrongCallBack(Map<String, String> redirect, String label) {
        redirect.put(label + OtherConstants.POSTFIX, "true");
    }
}
