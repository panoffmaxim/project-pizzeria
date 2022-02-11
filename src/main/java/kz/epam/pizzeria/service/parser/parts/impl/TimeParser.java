package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.TimeValidator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Dedicated to parse String to LocalDateTime for {@link DeliveryInf#getDeliveryTime()} value
 */
public class TimeParser extends ParamsParser<LocalDateTime> {
    private static TimeParser INSTANCE = new TimeParser();

    public static TimeParser getInstance() {
        return INSTANCE;
    }

    private static final String FORMAT = "HH:mm";

    private TimeParser() {
        super(TimeValidator.getInstance());
    }

    @Override
    protected LocalDateTime modify(String input) throws Exception {
        LocalTime localTime = LocalTime.parse(input, DateTimeFormatter.ofPattern(FORMAT));
        LocalDateTime time = LocalDateTime.now()
                .withHour(localTime.getHour())
                .withMinute(localTime.getMinute())
                .withSecond(0)
                .withNano(0);

        if (time.compareTo(LocalDateTime.now()) < 0) {
            time = time.plusDays(1);
        }
        return time;
    }
}
