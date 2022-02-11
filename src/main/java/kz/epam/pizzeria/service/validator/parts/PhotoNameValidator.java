package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

/**
 * Dedicated to validate {@link ProductGroup#getPhotoName()}
 */
public class PhotoNameValidator implements Validator<String> {
    private static PhotoNameValidator INSTANCE = new PhotoNameValidator();

    public static PhotoNameValidator getInstance() {
        return INSTANCE;
    }

    private PhotoNameValidator() {
    }

    private static final String PHOTO_NAME_REGEX = "[-\\p{javaAlphabetic}\\d\\s().]{1,30}(\\.png|\\.jpg|\\.jpeg|\\.gif)";
    private static final Pattern COMPILE = Pattern.compile(PHOTO_NAME_REGEX, Pattern.UNICODE_CASE);

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
