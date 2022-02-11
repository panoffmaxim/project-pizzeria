package kz.epam.pizzeria.service.factory;

import kz.epam.pizzeria.service.email.MailSender;
import kz.epam.pizzeria.service.email.impl.MailSenderImpl;
import kz.epam.pizzeria.service.encryption.ApplicationEncrypt;
import kz.epam.pizzeria.service.encryption.impl.ApplicationEncryptImpl;
import kz.epam.pizzeria.service.helper.ImageWriterService;
import kz.epam.pizzeria.service.helper.impl.ImageWriterServiceImpl;
import kz.epam.pizzeria.service.helper.PutItemService;
import kz.epam.pizzeria.service.helper.impl.PutItemServiceImpl;
import kz.epam.pizzeria.service.db.*;
import kz.epam.pizzeria.service.db.impl.*;
import kz.epam.pizzeria.service.pagination.PaginationService;
import kz.epam.pizzeria.service.pagination.impl.PaginationServiceImpl;
import kz.epam.pizzeria.service.parser.full.OrderParser;
import kz.epam.pizzeria.service.parser.full.ProductGroupParser;
import kz.epam.pizzeria.service.parser.full.ProductParser;
import kz.epam.pizzeria.service.parser.full.UserParser;
import kz.epam.pizzeria.service.parser.helper.NullIfEmptyService;
import kz.epam.pizzeria.service.pagination.PaginationCalculator;
import kz.epam.pizzeria.service.pagination.impl.PaginationCalculatorImpl;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;
import kz.epam.pizzeria.service.parser.full.impl.OrderParserImpl;
import kz.epam.pizzeria.service.parser.full.impl.ProductGroupParserImpl;
import kz.epam.pizzeria.service.parser.full.impl.ProductParserImpl;
import kz.epam.pizzeria.service.parser.full.impl.UserParserImpl;
import kz.epam.pizzeria.service.parser.helper.impl.NullIfEmptyServiceImpl;
import kz.epam.pizzeria.service.parser.helper.impl.PathVarCalculatorImpl;
import kz.epam.pizzeria.service.parser.parts.impl.IdParser;
import kz.epam.pizzeria.service.validator.parts.LoginValidator;

/**
 * Factory to give services for other layers
 */
public class ServiceFactory {
    private static ServiceFactory instance = new ServiceFactory();

    public static ServiceFactory getInstance() {
        return instance;
    }

    private ServiceFactory() {
    }

    private final IdParser idParser = IdParser.getInstance();
    private final DeliveryInfService deliveryInfService = new DeliveryInfServiceImpl();
    private final OrderService orderService = new OrderServiceImpl();
    private final ProductGroupService productGroupService = new ProductGroupServiceImpl();
    private final ProductService productServiceImpl = new ProductServiceImpl();
    private final UserService userService = new UserServiceImpl();
    private final PathVarCalculator pathVarCalculator = PathVarCalculatorImpl.getInstance();
    private final LoginValidator loginValidator = LoginValidator.getInstance();
    private final NullIfEmptyService nullIfEmptyService = NullIfEmptyServiceImpl.getInstance();
    private final PutItemService putItemService = PutItemServiceImpl.getInstance();
    private final UserParser userParser = new UserParserImpl();
    private final ProductParser productParser = new ProductParserImpl();
    private final ProductGroupParser productGroupParser = new ProductGroupParserImpl();
    private final OrderParser orderParser = new OrderParserImpl();
    private final MailSender mailSender = new MailSenderImpl();
    private final ApplicationEncrypt applicationEncrypt = ApplicationEncryptImpl.getInstance();
    private final PaginationService paginationService = new PaginationServiceImpl();
    private final PaginationCalculator paginationCalculator = new PaginationCalculatorImpl();
    private final ImageWriterService imageWriterService = ImageWriterServiceImpl.getInstance();

    public DeliveryInfService getDeliveryInfService() {
        return deliveryInfService;
    }

    public OrderService getOrderService() {
        return orderService;
    }

    public ProductGroupService getProductGroupService() {
        return productGroupService;
    }

    public ProductService getProductService() {
        return productServiceImpl;
    }

    public UserService getUserService() {
        return userService;
    }

    public PathVarCalculator getPathVarCalculator() {
        return pathVarCalculator;
    }

    public LoginValidator getLoginValidator() {
        return loginValidator;
    }

    public NullIfEmptyService getNullIfEmptyService() {
        return nullIfEmptyService;
    }

    public ImageWriterService getImageWriterService() {
        return imageWriterService;
    }

    public PutItemService getPutItemService() {
        return putItemService;
    }

    public UserParser getUserParser() {
        return userParser;
    }

    public ProductParser getProductParser() {
        return productParser;
    }

    public ProductGroupParser getProductGroupParser() {
        return productGroupParser;
    }

    public OrderParser getOrderParser() {
        return orderParser;
    }

    public PaginationCalculator getPaginationCalculator() {
        return paginationCalculator;
    }

    public PaginationService getPaginationService() {
        return paginationService;
    }

    public ApplicationEncrypt getApplicationEncrypt() {
        return applicationEncrypt;
    }

    public MailSender getMailSender() {
        return mailSender;
    }

    public IdParser getIdParser() {
        return idParser;
    }
}
