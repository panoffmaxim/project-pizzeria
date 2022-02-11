package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class PutItemClient extends Command {
    private static final Logger LOGGER = LogManager.getLogger(PutItemClient.class);

    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("begin method");
        try {
            String productIdSt = request.getParameter("variant");
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Order currentByUserId = orderService.findOrCreateCurrentByUserId(user.getId());
            LOGGER.debug("productIdSt = {}", productIdSt);
            Integer orderId = currentByUserId.getId();
            Integer prodId = Integer.valueOf(productIdSt);
            orderService.plusProduct(orderId, prodId);
            return new Redirect(request.getHeader("referer"), false);
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(500);
        }
    }
}
