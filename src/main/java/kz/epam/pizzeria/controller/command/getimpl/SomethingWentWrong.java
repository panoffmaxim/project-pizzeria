package kz.epam.pizzeria.controller.command.getimpl;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SomethingWentWrong extends Command {

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return new Forward("/errors/something_went_wrong.jsp");
    }
}