package kz.epam.pizzeria.command;

import kz.epam.pizzeria.utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public abstract class Command {

    public Command() {
    }

    public abstract ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException;
}
