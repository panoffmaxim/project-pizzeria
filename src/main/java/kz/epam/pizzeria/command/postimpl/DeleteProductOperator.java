package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import kz.epam.pizzeria.utils.impl.SendError;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class DeleteProductOperator extends Command {
    private static final Logger LOGGER = LogManager.getLogger(DeleteProductOperator.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.debug("begin method");
        try {
            String orderIdSt = request.getParameter("order_id");
            String productIdSt = request.getParameter("id");
            LOGGER.debug("orderIdSt = {}", orderIdSt);
            LOGGER.debug("productIdSt = {}", productIdSt);
            Integer orderId = Integer.valueOf(orderIdSt);
            Integer prodId = Integer.valueOf(productIdSt);
            orderService.deleteProduct(orderId, prodId);
            return new Redirect("/add-products/" + orderIdSt + "?pagination=1");
        } catch (NumberFormatException | ServiceException e) {
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }
}
