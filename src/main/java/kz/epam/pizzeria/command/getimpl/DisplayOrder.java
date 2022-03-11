package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.dto.UserDTO;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.enums.PaymentType;
import kz.epam.pizzeria.entity.enums.Role;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class DisplayOrder extends Command {
    private static final Logger LOGGER = LogManager.getLogger(DisplayOrder.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final OrderService orderService = serviceFactory.getOrderService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Map<Product, Integer> basket = takeBasket(request);
            request.setAttribute("productMap", basket);
            request.setAttribute("types", PaymentType.values());
            request.setAttribute("sum", calcSum(basket));
            sendUserDTO(request);
            return new Forward("/order.jsp");
        } catch (ServiceException e) {
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }

    private void sendUserDTO(HttpServletRequest request) {
        HttpSession session = request.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null) {
            request.setAttribute("info", new UserDTO(user));
        }
    }

    private Integer calcSum(Map<Product, Integer> basket) {
        return basket.entrySet().stream()
                .map(e -> e.getKey().getPrice() * e.getValue())
                .reduce(0, Integer::sum);
    }

    private Map<Product, Integer> takeBasket(HttpServletRequest req) throws ServiceException {
        HttpSession session = req.getSession();
        LOGGER.debug("session took");
        Role role = (Role) req.getAttribute("role");
        LOGGER.debug("role = {}", role);
        if (role == Role.ANON) {
            return returnAnonBasket(session);
        } else if (role == Role.CLIENT) {
            return returnClientBasket(session);
        } else {
            return new HashMap<>();
        }
    }

    private Map<Product, Integer> returnAnonBasket(HttpSession session) {
        Map<Product, Integer> basket;
        Object basketObj = session.getAttribute("basket");
        if (basketObj == null) {
            basket = new HashMap<>();
        } else {
            basket = ((Map<Product, Integer>) basketObj);
        }
        return basket;
    }

    private Map<Product, Integer> returnClientBasket(HttpSession session) throws ServiceException {
        User user = (User) session.getAttribute("user");
        Order order = orderService.findCurrentByUserId(user.getId());
        if (order == null) {
            return new HashMap<>();
        }
        return order.getProducts();
    }
}
