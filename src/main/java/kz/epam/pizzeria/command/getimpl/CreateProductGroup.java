package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.utils.impl.SendError;
import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class CreateProductGroup extends Command {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            List<Product> emptyProd = productService.findAllByProductGroupNull();
            request.setAttribute("products", emptyProd);
            request.setAttribute("types", ProductType.values());
            return new Forward("/admin/create-product-group.jsp");
        } catch (ServiceException e) {
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }
}
