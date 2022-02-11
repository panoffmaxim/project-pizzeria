package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.DateTimeValidator;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DateTimeParser extends ParamsParser<LocalDateTime> {
    private static final Logger LOGGER = LogManager.getLogger(DateTimeParser.class);
    private static DateTimeParser INSTANCE = new DateTimeParser();

    public static DateTimeParser getInstance() {
        return INSTANCE;
    }

    private static final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm");

    private DateTimeParser() {
        super(DateTimeValidator.getInstance());
    }

    @Override
    protected LocalDateTime modify(String input) throws Exception {
        LOGGER.debug("modify: input = {}", input);
        LocalDateTime localDateTime = LocalDateTime.parse(input, formatter);
        LOGGER.debug("modify: localDateTime = {}", localDateTime);
        return localDateTime
                .withSecond(0)
                .withNano(0);
    }
}
