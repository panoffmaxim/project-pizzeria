package kz.epam.pizzeria.constant;

import java.time.Duration;

public class ValidatorConstants {
    public static final int FLOOR_MIN = -100;
    public static final int FLOOR_MAX = 100;
    public static final int HOUSE_NUMBER_MAX_LENGTH = 10;
    public static final int ID_MIN_EXCLUDE_VALUE = 0;
    public static final int PORCH_MIN_VALUE = 0;
    public static final int PORCH_MAX_VALUE = 50;
    public static final int PRICE_MAX_VALUE = 1_000_000_000;
    public static final int PRICE_MIN_VALUE = 0;
    public static final int PRODUCT_GROUP_MIN_VALUE = 0;
    public static final int STREET_NAME_MAX_LENGTH = 50;
    public static final int WEIGHT_MAX = 1_000_000;
    public static final int WEIGHT_MIN = 0;
    public static final Duration MIN_DIFFERENCE = Duration.ofMinutes(30);
    public static final Duration MAX_DIFFERENCE = Duration.ofHours(24);
    public static final int BASKET_MIN_VALUE = 1;
}
