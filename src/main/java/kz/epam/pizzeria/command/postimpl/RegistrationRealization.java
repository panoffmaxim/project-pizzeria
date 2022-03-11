package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.filter.RedirectFilter;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.full.UserParser;
import kz.epam.pizzeria.service.parser.helper.NullIfEmptyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class RegistrationRealization extends Command {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationRealization.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();
    private final NullIfEmptyService nullEmpt = serviceFactory.getNullIfEmptyService();
    private final UserParser userParser = serviceFactory.getUserParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("execute: begin");
        Map<String, String> redirect = new HashMap<>();
        User build = validateAndTakeParams(request, redirect);
        LOGGER.debug("execute: build = {}", build);
        if (build != null) {
            try {
                if (userService.create(build) != null) {
                    return new Redirect("/login");
                } else {
                    return new SendError(OtherConstants.STATUS_CODE_403);
                }
            } catch (ServiceException e) {
                LOGGER.debug("Exception due invoking RegistrationRealization ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
        return new SendError(OtherConstants.STATUS_CODE_403);
    }

    private User validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect) {
        String email = request.getParameter("email");
        String phone = request.getParameter("phone");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String street = request.getParameter("street");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String token = request.getParameter("token");
        return userParser.parseRegistrationUserWithToken(redirect, email, phone, username, password, name, surname, street,
                house, room, porch, floor, token);
    }
}
