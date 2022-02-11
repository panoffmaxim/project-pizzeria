package kz.epam.pizzeria.controller.command.getimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Forward;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.db.OrderService;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.IllegalPathParamException;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.pagination.PaginationCalculator;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class AddProducts extends Command {
    private static final Logger LOGGER = LogManager.getLogger(AddProducts.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final ProductService productService = serviceFactory.getProductService();
    //    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();
    private final OrderService orderService = serviceFactory.getOrderService();
    private final PaginationCalculator paginationCalculator = serviceFactory.getPaginationCalculator();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int part = paginationCalculator.calculatePartParam(request.getParameter("pagination"));
            Integer pathVar = pathVarCalculator.findLastInteger(request.getPathInfo());
            Order order = orderService.findEntityById(pathVar);
            Map<Product, Integer> products = order.getProducts();
            Comparator<Map.Entry<Product, Integer>> fstComparator = Comparator.comparing(e -> e.getKey().getProductGroup().getId());
            Comparator<Map.Entry<Product, Integer>> sec = Comparator.comparing(e -> e.getKey().getWeight());
            Comparator<Map.Entry<Product, Integer>> fin = fstComparator.thenComparing(sec);
            List<Map.Entry<Product, Integer>> all = productService.findAllByProductGroupNotDisabled().stream()
                    .filter(p -> p.getProductGroup() != null)
                    .filter(p -> {
                        for (Product product : products.keySet()) {
                            if (product.getId().equals(p.getId())) {
                                return false;
                            }
                        }
                        return true;
                    })
                    .map(p -> Map.entry(p, 0))
                    .sorted(fin)
                    .collect(Collectors.toList());
            LOGGER.debug("products = {}", products);
            LOGGER.debug("all = {}", all);
            request.setAttribute("contain_products", products);
            request.setAttribute("not_contain_products", all);
            request.setAttribute("sum", order.getPrice());
            request.setAttribute("id", order.getId());
            LOGGER.debug("all = {}", all);
            return new Forward("/add-products.jsp");
        } catch (ServiceException e) {
            return new SendError(500);
        }
    }
}