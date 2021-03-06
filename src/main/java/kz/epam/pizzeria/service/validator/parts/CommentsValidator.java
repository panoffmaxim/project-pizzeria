package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.validator.Validator;
import java.util.regex.Pattern;

public class CommentsValidator implements Validator<String> {
    private static CommentsValidator INSTANCE = new CommentsValidator();
    private static final Pattern COMPILE = Pattern.compile("[\\s\\S]{0,200}");

    public static CommentsValidator getInstance() {
        return INSTANCE;
    }

    private CommentsValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return input == null || COMPILE.matcher(input).matches();
    }
}
