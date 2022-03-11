package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class LogOut extends Command {

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        session.setAttribute("user", null);
        return new Redirect("");
    }
}
