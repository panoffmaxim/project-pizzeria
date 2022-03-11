package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.filter.RedirectFilter;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.encryption.ApplicationEncrypt;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Login extends Command {
    private static final Logger LOGGER = LogManager.getLogger(Login.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ApplicationEncrypt applicationEncrypt = serviceFactory.getApplicationEncrypt();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String targetUrl = request.getParameter("target_url");
        HttpSession session = request.getSession();
        Map<String, String> redirect = new HashMap<>();
        try {
            User user = userService.findUserByUsername(username);
            if (user != null && user.getPassword().equals(applicationEncrypt.calcUserPasswordHash(password))) {
                putUser(request, user);
                session.removeAttribute("basket");
                return redirect(request, targetUrl);
            }
        } catch (ServiceException e) {
            LOGGER.debug("Exception due invoking Login ", e);
        }
        redirect.put("authentication_error", "true");
        redirect.put("target_url", targetUrl);
        session.setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
        return new Redirect("/login");
    }

    private ResponseObject redirect(HttpServletRequest request, String targetUrl) {
        LOGGER.debug("targetUrl = {}", targetUrl);
        if (targetUrl != null && !targetUrl.isEmpty() && targetUrl.startsWith(request.getContextPath())) {
            return new Redirect(targetUrl, false);
        }
        return new Redirect("/");
    }

    private void putUser(HttpServletRequest request, User user) {
        HttpSession session = request.getSession();
        session.setAttribute("user", user);
    }
}
