package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.enums.Role;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class EditUser extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditUser.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            LOGGER.info("execute: id = {}", id);
            User user = userService.findEntityById(id);
            LOGGER.info("execute: user = {}", user);
            if (user != null) {
                request.setAttribute("user", user);
                request.setAttribute("roles", Role.values());
                return new Forward("/admin/edit-user.jsp");
            }
        } catch (ServiceException e) {
            LOGGER.debug("Exception due invoking EditUser ", e);
        }
        return new SendError(OtherConstants.STATUS_CODE_500);
    }
}
