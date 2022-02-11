package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.full.UserParser;
import kz.epam.pizzeria.service.parser.parts.impl.IdParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import static kz.epam.pizzeria.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class EditAdmin extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditAdmin.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();
    private final UserParser userParser = serviceFactory.getUserParser();
    private final IdParser idParser = serviceFactory.getIdParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("Begin  EditAdminCommand");
        String referer = request.getHeader("referer");
        Map<String, String> redirect = new HashMap<>();
        try {
            User user = findUser(request);
            if (validateAndTakeParams(request, redirect, user)) {
                if (userService.update(user)) {
                    return new Redirect("/admin/user-list?pagination=1");
                }
                redirect.put("unknown_error", "true");
            }
        } catch (ServiceException e) {
            LOGGER.debug("e: ", e);
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private boolean validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect, User user) {
        String username = request.getParameter("username");
        String role = request.getParameter("role");
        String name = request.getParameter("name");
        String surname = request.getParameter("surname");
        String house = request.getParameter("house");
        String room = request.getParameter("room");
        String porch = request.getParameter("porch");
        String floor = request.getParameter("floor");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");
        String street = request.getParameter("street");
        String isBlocked = request.getParameter("isBlocked");
        LOGGER.info("isBlocked = {}", isBlocked);
        return userParser.parseUserWithId(redirect, user, username, role, name, surname, house, room, porch, floor, phone,
                email, street, isBlocked);
    }

    private User findUser(HttpServletRequest request) throws ServiceException {
        String idParam = request.getParameter("id");
        OptionalNullable<Integer> parse = idParser.parse(idParam);
        if (parse.isPresent()) {
            return userService.findEntityById(parse.get());
        }
        return null;
    }
}
