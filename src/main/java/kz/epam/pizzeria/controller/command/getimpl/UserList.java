package kz.epam.pizzeria.controller.command.getimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.pagination.PaginationService;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.pagination.PaginationCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static kz.epam.pizzeria.config.Configuration.MAX_PAGINATION_ELEMENTS;

public class UserList extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserList.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();
    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();
    private final PaginationService paginationService = serviceFactory.getPaginationService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));
            List<User> all = userService.findAllByPart(part);
            LOGGER.info("execute: all = {}", all);
            request.setAttribute("users", all);
            request.setAttribute("paginationMap", paginationService.calculate(userService.count(), part, MAX_PAGINATION_ELEMENTS));
            return new Forward("/admin/user-list.jsp");
        } catch (ServiceException e) {
            return new SendError(500);
        }
    }
}
