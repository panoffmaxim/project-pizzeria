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

public class CancelOrder extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CancelOrder.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("id");
        try {
            Integer idInt = Integer.valueOf(id);
            boolean update = orderService.cancelOrDeleteById(idInt);
            LOGGER.debug("execute: update = {}", update);
            return new Redirect("/order-list?pagination=1");
        } catch (NumberFormatException | ServiceException e) {
            LOGGER.debug("e: ", e);
            return new SendError(500);
        }
    }
}
