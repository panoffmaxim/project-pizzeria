package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.filter.RedirectFilter;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.full.ProductParser;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class EditProduct extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditProduct.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();
    private final ProductParser productParser = serviceFactory.getProductParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        LOGGER.debug("begin method");
        Map<String, String> redirect = new HashMap<>();
        Product build = validateAndTakeParams(request, redirect);
        if (build != null) {
            try {
                if (productService.update(build)) {
                    return new Redirect("/admin/product-list?pagination=1");
                }
            } catch (ServiceException e) {
                LOGGER.debug("Exception due invoking EditProduct ", e);
            }
            redirect.put("unknown_error", "true");
        }
        request.getSession().setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private Product validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect) {
        String id = request.getParameter("id");
        String productGroup = request.getParameter("product_group");
        String price = request.getParameter("price");
        String weight = request.getParameter("weight");
        return productParser.parseProductWithId(redirect, id, productGroup, price, weight);
    }
}
