package kz.epam.pizzeria.service.parser.parts.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.service.parser.parts.ParamsParser;
import kz.epam.pizzeria.service.validator.parts.TimeValidator;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class TimeParser extends ParamsParser<LocalDateTime> {
    private static TimeParser INSTANCE = new TimeParser();

    public static TimeParser getInstance() {
        return INSTANCE;
    }

    private TimeParser() {
        super(TimeValidator.getInstance());
    }

    @Override
    protected LocalDateTime modify(String input) throws Exception {
        LocalTime localTime = LocalTime.parse(input, DateTimeFormatter.ofPattern(OtherConstants.FORMAT));
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
