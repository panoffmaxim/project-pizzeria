package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.Product;
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

public class MakeOrderAnon extends Command {
    private static final Logger LOGGER = LogManager.getLogger(MakeOrderAnon.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final OrderParser orderParser = serviceFactory.getOrderParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        Map<String, String> redirect = new HashMap<>();
        HttpSession session = request.getSession();
        Order build = buildOrder(request, session, redirect);
        if (build != null) {
            try {
                if (orderService.create(build) != null) {
                    LOGGER.debug("first2");
                    session.setAttribute("basket", null);
                    return new Redirect("/your-order/" + build.getId());
                }
            } catch (ServiceException e) {
                LOGGER.debug("e: ", e);
            }
            redirect.put("unknown_error", "true");
        }
        session.setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private Order buildOrder(HttpServletRequest request, HttpSession session, Map<String, String> redirect) {
        Map<Product, Integer> basket = takeBasket(session);
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
        return orderParser.parse(redirect, street, comments, floor, porch, room, house, name, tel, email, time, basket,paymentType);
    }

    private Map<Product, Integer> takeBasket(HttpSession session) {
        Map<Product, Integer> basket;
        Object basketObj = session.getAttribute("basket");
        if (basketObj == null) {
            basket = new HashMap<>();
        } else {
            basket = ((Map<Product, Integer>) basketObj);
        }
        return basket;
    }
}
