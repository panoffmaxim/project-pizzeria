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
import kz.epam.pizzeria.service.pagination.PaginationService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.pagination.PaginationCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class OrderList extends Command {
    private static final Logger LOGGER = LogManager.getLogger(OrderList.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();
    private final PaginationService paginationService = serviceFactory.getPaginationService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));
            List<Order> all = orderService.findAllByPart(part);
            LOGGER.info("execute: all = {}", all);
            request.setAttribute("orders", all);
            request.setAttribute("paginationMap", paginationService.calculate(orderService.count(), part, OtherConstants.MAX_PAGINATION_ELEMENTS));
            return new Forward("/order-list.jsp");
        } catch (ServiceException e) {
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }
}
