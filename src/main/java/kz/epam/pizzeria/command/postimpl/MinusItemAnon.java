package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class MinusItemAnon extends Command {
    private static final Logger LOGGER = LogManager.getLogger(MinusItemAnon.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            String referer = request.getHeader("referer");
            String id = request.getParameter("variant");
            Integer prodId = Integer.valueOf(id);
            HttpSession session = request.getSession();
            Map<Product, Integer> basket = takeBasket(session);
            Product entityById = productService.findEntityById(prodId);
            if (entityById != null) {
                minusProduct(basket, entityById);
                commitSession(basket, session);
                return new Redirect(referer, false);
            }
        } catch (NumberFormatException | ServiceException e) {
            LOGGER.debug("Exception due invoking MinusItemAnon ", e);
        }
        return new SendError(OtherConstants.STATUS_CODE_500);
    }

    private void commitSession(Map<Product, Integer> basket, HttpSession session) {
        session.setAttribute("basket", basket);
    }

    private void minusProduct(Map<Product, Integer> basket, Product entityById) {
        Optional<Product> any = basket.keySet().stream()
                .filter(p -> p.getId().equals(entityById.getId()))
                .findAny();
        if (any.isPresent()) {
            Product product = any.get();
            int resultNum = basket.get(product) - 1;
            if (resultNum == 0) {
                basket.remove(product);
            } else {
                basket.put(product, resultNum);
            }
        }
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
