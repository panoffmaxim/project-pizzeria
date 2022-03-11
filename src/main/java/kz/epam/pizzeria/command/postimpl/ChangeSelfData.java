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
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ChangeSelfData extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeSelfData.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();
    private final UserParser userParser = serviceFactory.getUserParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, String> redirect = new HashMap<>();
        HttpSession session = request.getSession();
        User user = ((User) session.getAttribute("user"));
        boolean valid = validateAndTakeParams(request, redirect, user);
        if (valid) {
            try {
                if (userService.update(user)) {
                    return new Redirect("/cabinet");
                }
            } catch (ServiceException e) {
                LOGGER.debug("Exception due invoking ChangeSelfData ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
        return new Redirect("/cabinet");
    }

    private boolean validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect, User base) {
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String phone = request.getParameter("phone");
        String street = request.getParameter("street");
        boolean parse = userParser.parseWithBaseSelfChange(redirect, base, name, surname, house, room, porch, floor, phone, street);
        String passwordOld = request.getParameter("old_password");
        String passwordNew = request.getParameter("new_password");
        if (passwordOld != null && passwordNew != null) {
            parse = userParser.parseChangePassword(redirect, base, passwordOld, passwordNew) && parse;
        }
        return parse;
    }
}
