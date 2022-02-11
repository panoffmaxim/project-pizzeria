package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class PlusProductOperator extends Command {
    private static final Logger LOGGER = LogManager.getLogger(PlusProductOperator.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("begin method");
        try {
            String orderIdSt = request.getParameter("order_id");
            String productIdSt = request.getParameter("product_id");
            LOGGER.debug("orderIdSt = {}", orderIdSt);
            LOGGER.debug("productIdSt = {}", productIdSt);
            Integer orderId = Integer.valueOf(orderIdSt);
            Integer prodId = Integer.valueOf(productIdSt);
            orderService.plusProduct(orderId, prodId);
            return new Redirect("/add-products/" + orderIdSt + "?pagination=1");
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(500);
        }
    }
}
