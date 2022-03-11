package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.filter.RedirectFilter;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.full.UserParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class CreateUser extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateUser.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();
    private final UserParser userParser = serviceFactory.getUserParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        Map<String, String> redirect = new HashMap<>();
        User build = validateAndTakeParams(request, redirect);
        if (build != null) {
            try {
                if (userService.create(build) != null) {
                    return new Redirect("/admin/user-list?pagination=1");
                }
            } catch (ServiceException e) {
                LOGGER.debug("Exception due invoking CreateUser  ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private User validateAndTakeParams(HttpServletRequest req, Map<String, String> redirect) {
        String usernameParam = req.getParameter("username");
        String passwordParam = req.getParameter("password");
        String roleParam = req.getParameter("role");
        String nameParam = req.getParameter("name");
        String surnameParam = req.getParameter("surname");
        String houseParam = req.getParameter("house");
        String roomParam = req.getParameter("room");
        String porchParam = req.getParameter("porch");
        String floorParam = req.getParameter("floor");
        String phoneParam = req.getParameter("phone");
        String emailParam = req.getParameter("email");
        String streetParam = req.getParameter("street");
        return userParser.parseUser(redirect, usernameParam, passwordParam, roleParam, nameParam, surnameParam, houseParam,
                roomParam, porchParam, floorParam, phoneParam, emailParam, streetParam);
    }
}
