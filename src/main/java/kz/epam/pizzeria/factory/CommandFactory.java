package kz.epam.pizzeria.factory;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.factory.exception.PageNotFoundException;

public interface CommandFactory {
    Command create(String key) throws PageNotFoundException;
}
