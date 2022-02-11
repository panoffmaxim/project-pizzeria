package kz.epam.pizzeria.service.validator.parts;

import kz.epam.pizzeria.service.validator.Validator;

import java.util.regex.Pattern;

public class PhotoNameValidator implements Validator<String> {
    private static PhotoNameValidator INSTANCE = new PhotoNameValidator();
    private static final String PHOTO_NAME_REGEX = "[-\\p{javaAlphabetic}\\d\\s().]{1,30}(\\.png|\\.jpg|\\.jpeg|\\.gif)";
    private static final Pattern COMPILE = Pattern.compile(PHOTO_NAME_REGEX, Pattern.UNICODE_CASE);

    public static PhotoNameValidator getInstance() {
        return INSTANCE;
    }

    private PhotoNameValidator() {
    }

    @Override
    public boolean isValid(String input) {
        return input != null && COMPILE.matcher(input).matches();
    }
}
