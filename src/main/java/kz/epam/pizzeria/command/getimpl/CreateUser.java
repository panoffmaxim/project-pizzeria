package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class CreateUser extends Command {

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setAttribute("roles", Role.values());
        return new Forward("/admin/create-user.jsp");
    }
}
