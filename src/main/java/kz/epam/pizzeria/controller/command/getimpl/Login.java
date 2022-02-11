package kz.epam.pizzeria.controller.command.getimpl;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class Login extends Command {

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
//        String tarUrl = (String) session.getAttribute("target_url");
//        if (tarUrl != null && !tarUrl.isEmpty()) {
//            request.setAttribute("target_url", tarUrl);
//            session.removeAttribute("target_url");
//        }
        return new Forward("/login.jsp");
    }
}
