package kz.epam.pizzeria.controller.command.getimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.db.ProductGroupService;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.IllegalPathParamException;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditProduct extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditProduct.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final ProductService productService = serviceFactory.getProductService();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            Product product = productService.findEntityById(id);
            if (product != null) {
                request.setAttribute("product", product);
                List<ProductGroup> allExcept = productGroupService.findAllExcept(product.getProductGroup());
                request.setAttribute("groups", allExcept);
                return new Forward("/admin/edit-product.jsp");
            }
        } catch (ServiceException e) {
            LOGGER.debug("e: ", e);
        }
        return new SendError(500);
    }
}