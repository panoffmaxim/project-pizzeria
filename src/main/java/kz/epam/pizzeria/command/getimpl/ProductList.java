package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Forward;
import kz.epam.pizzeria.utils.impl.SendError;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.pagination.PaginationService;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.pagination.PaginationCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public class ProductList extends Command {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductService productService = serviceFactory.getProductService();
    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();
    private final PaginationService paginationService = serviceFactory.getPaginationService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));
            List<Product> all = productService.findAllByPart(part);
            request.setAttribute("products", all);
            request.setAttribute("paginationMap", paginationService.calculate(productService.findAll().size(), part, OtherConstants.MAX_PAGINATION_ELEMENTS));
            return new Forward("/admin/product-list.jsp");
        } catch (ServiceException e) {
            return new SendError(OtherConstants.STATUS_CODE_500);
        }
    }
}
