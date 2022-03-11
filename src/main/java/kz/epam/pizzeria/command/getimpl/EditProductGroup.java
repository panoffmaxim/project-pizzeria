package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.db.ProductGroupService;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class EditProductGroup extends Command {
    private static final Logger LOGGER = LogManager.getLogger(EditProductGroup.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            ProductGroup productGroup = productGroupService.findEntityById(id);
            if (productGroup != null) {
                request.setAttribute("group", productGroup);
                request.setAttribute("types", ProductType.values());
                putProducts(request);
                return new Forward("/admin/edit-product-group.jsp");
            }
        } catch (ServiceException e) {
            LOGGER.debug("ServiceException due invoking EditProductGroup ", e);
        }
        return new SendError(OtherConstants.STATUS_CODE_500);
    }

    private void putProducts(HttpServletRequest request) throws ServiceException {
        List<Product> emptyProducts = productService.findAllByProductGroupNull();
        request.setAttribute("products", emptyProducts);
    }
}
