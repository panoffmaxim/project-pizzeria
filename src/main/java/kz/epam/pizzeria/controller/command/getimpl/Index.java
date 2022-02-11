package kz.epam.pizzeria.controller.command.getimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.db.ProductGroupService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static kz.epam.pizzeria.controller.command.getimpl.AddProducts.STATUS_CODE_500;

public class Index extends Command {
    private static final Logger LOGGER = LogManager.getLogger(Index.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ProductType prodType = calcVariables(request);
        LOGGER.info("execute: prodType = {}", prodType);
        try {
            List<ProductGroup> list = productGroupService.findAllByProductTypeNotDisabled(prodType);
            Map<ProductGroup, Integer> result = list.stream()
                    .collect(Collectors.toMap(p -> p, this::getMinPrice));
            request.setAttribute("products", result);
            LOGGER.info("execute: result = {}", result);
            return new Forward("/index.jsp");
        } catch (ServiceException e) {
            LOGGER.error("Error:", e);
            return new SendError(STATUS_CODE_500);
        }
    }

    private Integer getMinPrice(ProductGroup p) {
        return p.getProducts().stream()
                .map(Product::getPrice)
                .min(Integer::compareTo)
                .orElse(0);
    }

    private ProductType calcVariables(HttpServletRequest request) {
        String type = request.getParameter("type");
        ProductType prodType;
        try {
            prodType = ProductType.valueOf(type);
        } catch (IllegalArgumentException | NullPointerException e) {
            LOGGER.info("Parameter exception: ", e);
            prodType = ProductType.PIZZA;
        }
        return prodType;
    }
}
