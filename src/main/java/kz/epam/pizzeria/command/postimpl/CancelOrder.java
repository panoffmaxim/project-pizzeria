package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

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
            LOGGER.debug("Parameter exception: ", e);
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }
}
