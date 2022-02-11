package kz.epam.pizzeria.controller.factory.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.CommandDecorator;
import kz.epam.pizzeria.controller.command.getimpl.*;
import kz.epam.pizzeria.controller.factory.CommandFactory;
import kz.epam.pizzeria.controller.factory.exception.PageNotFoundException;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static kz.epam.pizzeria.entity.enums.Role.*;

public class CommandGetFactory implements CommandFactory {

    private CommandGetFactory() {
    }

    private static CommandGetFactory instance = new CommandGetFactory();

    public static CommandGetFactory getInstance() {
        return instance;
    }

    private static final Logger LOGGER = LogManager.getLogger(CommandGetFactory.class);

    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    public static final String LOGIN_PAGE = "/login";

    static {
        commandMap.put("/something_went_wrong", new SomethingWentWrong());
        /*language=RegExp*/
        commandMap.put("/?", new Index());
        commandMap.put("/order", new DisplayOrder());
        commandMap.put(LOGIN_PAGE, new CommandDecorator(new Login(), EnumSet.of(ANON)));
        commandMap.put("/cabinet", new CommandDecorator(new Cabinet(), EnumSet.of(CLIENT, OPERATOR, ADMIN)));
        /*language=RegExp*/
        commandMap.put("/edit-order/\\d+", new CommandDecorator(new EditOrder(), EnumSet.of(OPERATOR)));
        commandMap.put("/order-list", new CommandDecorator(new OrderList(), EnumSet.of(OPERATOR)));
        commandMap.put("/registration", new Registration());
        /*language=RegExp*/
        commandMap.put("/your-order/\\d+", new YourOrder());
        commandMap.put("/admin/create-product", new CommandDecorator(new CreateProduct(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/create-product-group", new CommandDecorator(new CreateProductGroup(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/create-user", new CommandDecorator(new CreateUser(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/user-list", new CommandDecorator(new UserList(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/product-list", new CommandDecorator(new ProductList(), EnumSet.of(ADMIN)));
        commandMap.put("/admin/product-group-list", new CommandDecorator(new ProductGroupList(), EnumSet.of(ADMIN)));
        /*language=RegExp*/
        commandMap.put("/admin/edit-user/\\d+", new CommandDecorator(new EditUser(), EnumSet.of(ADMIN)));
        /*language=RegExp*/
        commandMap.put("/admin/edit-product-group/\\d+", new CommandDecorator(new EditProductGroup(), EnumSet.of(ADMIN)));
        /*language=RegExp*/
        commandMap.put("/admin/edit-product/\\d+", new CommandDecorator(new EditProduct(), EnumSet.of(ADMIN)));
        commandMap.put("/add-products/\\d+", new AddProducts());
        commandMap.put("/logout", new CommandDecorator(new LogOut(), EnumSet.complementOf(EnumSet.of(ANON))));
//        commandMap.put("/permission-denied", new PermissionDeniedCommand());
    }

    @Override
    public Command create(String key) throws PageNotFoundException {
        for (Map.Entry<String, Command> s : commandMap.entrySet()) {
            LOGGER.info("s.getKey() = {}", s.getKey());
            LOGGER.info("s.getValue() = {}", s.getValue());
            LOGGER.info("key = {}", key);
            if (Pattern.compile(s.getKey()).matcher(key).matches()) {
                return s.getValue();
            }
        }
        throw new PageNotFoundException("Page not found.");
    }
}
