package kz.epam.pizzeria.controller.command.getimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.enums.OrderStatus;
import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.IllegalPathParamException;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.EnumSet;

public class EditOrder extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditOrder.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final OrderService orderService = serviceFactory.getOrderService();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'hh:mm");
//    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-mm-ddThh:mm");

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            LOGGER.info("execute: id = {}", id);
            Order order = orderService.findEntityById(id);
            if (order != null) {
                request.setAttribute("order", order);
                request.setAttribute("statuses", EnumSet.complementOf(EnumSet.of(OrderStatus.WAITING)));
                request.setAttribute("types", PaymentType.values());
                request.setAttribute("time", parseToTime(order.getDeliveryInf().getDeliveryTime()));
                return new Forward("/edit-order.jsp");
            } else {
                LOGGER.info("order is null");
                return new SendError(500);
            }
        } catch (ServiceException e) {
            LOGGER.info("Problem in parsing");
            return new Forward("/edit-order.jsp");
        }
    }

    private String parseToTime(LocalDateTime deliveryTime) {
        LOGGER.debug("deliveryTime = {}", deliveryTime);
        String timeSt = deliveryTime.format(formatter);
        LOGGER.debug("timeSt = {}", timeSt);
        return timeSt;
    }
}