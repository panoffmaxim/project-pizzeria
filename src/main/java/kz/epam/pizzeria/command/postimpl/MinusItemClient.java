package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class MinusItemClient extends Command {
    private static final Logger LOGGER = LogManager.getLogger(MinusItemClient.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("begin method");
        try {
            String productIdSt = request.getParameter("variant");
            LOGGER.debug("productIdSt = {}", productIdSt);
            HttpSession session = request.getSession();
            User user = (User) session.getAttribute("user");
            Order currentByUserId = orderService.findOrCreateCurrentByUserId(user.getId());
            Integer orderId = currentByUserId.getId();
            Integer prodId = Integer.valueOf(productIdSt);
            orderService.minusOrDelete(orderId, prodId);
            return new Redirect("/order");
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }
}
