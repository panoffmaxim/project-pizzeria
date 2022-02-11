package kz.epam.pizzeria.controller.factory;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.factory.exception.PageNotFoundException;

public interface CommandFactory {
    Command create(String key) throws PageNotFoundException;
}
