package kz.epam.pizzeria.controller.command.getimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.factory.impl.CommandGetFactory;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.pizzeria.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class PermissionDenied extends Command {
    private static final Logger LOGGER = LogManager.getLogger(PermissionDenied.class);
    public static final int STATUS_CODE_403 = 403;

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = ((Role) request.getAttribute("role"));
        HttpSession session = request.getSession();
        Map<String, String> redirect = new HashMap<>();
        if (role == Role.ANON) {
            String targetUrl = request.getRequestURI() + "?" + request.getQueryString();
            LOGGER.debug("targetUrl = {}", targetUrl);
            redirect.put("target_url", targetUrl);
            session.setAttribute(REDIRECTED_INFO, redirect);
            return new Redirect(CommandGetFactory.LOGIN_PAGE);
        } else {
            return new SendError(STATUS_CODE_403);
        }
    }
}
