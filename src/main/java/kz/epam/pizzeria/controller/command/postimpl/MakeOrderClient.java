package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.full.OrderParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.*;

import static kz.epam.pizzeria.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class MakeOrderClient extends Command {
    private static final Logger LOGGER = LogManager.getLogger(MakeOrderClient.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        Map<String, String> redirect = new HashMap<>();
        try {
            Order order = orderService.findCurrentByUserId(user.getId());
            if (order != null) {
                return update(request, response, referer, redirect, order);
            }
        } catch (ServiceException e) {
            LOGGER.debug("e: ", e);
        }
        redirect.put("no_products_error", "true");
        session.setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private ResponseObject update(HttpServletRequest request, HttpServletResponse response, String referer,
                                  Map<String, String> redirect, Order order) {
        if (buildOrder(request, order, redirect)) {
            try {
                if (orderService.update(order)) {
                    return new Redirect("/your-order/" + order.getId());
                }
            } catch (ServiceException e) {
                LOGGER.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private boolean buildOrder(HttpServletRequest request, Order order, Map<String, String> redirect) {
        String street = request.getParameter("street");
        String comments = request.getParameter("comments");
        String floor = request.getParameter("floor");
        String porch = request.getParameter("porch");
        String room = request.getParameter("room");
        String house = request.getParameter("house");
        String name = request.getParameter("name");
        String tel = request.getParameter("tel");
        String email = request.getParameter("email");
        String time = request.getParameter("time");
        String paymentType = request.getParameter("payment_type");
        return orderParser.parseWithBase(redirect, order, street, comments, floor, porch, room, house, name, tel, email, time, paymentType);
    }
}
