package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.PhotoNameValidator;

/**
 * Dedicated to parse String to {@link ProductGroup#getPhotoName()} value
 */
public class PhotoNameParser extends ParamsParser<String> {
    private static PhotoNameParser INSTANCE = new PhotoNameParser();

    public static PhotoNameParser getInstance() {
        return INSTANCE;
    }

    private PhotoNameParser() {
        super(PhotoNameValidator.getInstance());
    }

    @Override
    protected String modify(String input) throws Exception {
        return input;
    }
}
