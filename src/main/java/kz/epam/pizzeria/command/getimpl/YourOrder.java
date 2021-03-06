package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class YourOrder extends Command {
    private static final Logger LOGGER = LogManager.getLogger(YourOrder.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            Order order = orderService.findEntityById(id);
            if (order != null) {
                LOGGER.info("productMap = {}", order.getProducts());
                LOGGER.info("order.getProducts() = {}", order.getProducts());
                Integer sum = order.getProducts().entrySet().stream()
                        .map(p -> p.getKey().getPrice() * p.getValue())
                        .reduce(0, Integer::sum);
                request.setAttribute("order", order);
                request.setAttribute("sum", sum);
                LOGGER.debug("order.getProducts() = {}", order.getProducts());
                request.setAttribute("productMap", order.getProducts());
                return new Forward("/your-order.jsp");
            }
        } catch (ServiceException e) {
            LOGGER.debug("Exception due invoking YourOrder ", e);
        }
        return new SendError(OtherConstants.STATUS_CODE_500);
    }
}
