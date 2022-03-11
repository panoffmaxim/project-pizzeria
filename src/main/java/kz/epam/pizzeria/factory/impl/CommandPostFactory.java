package kz.epam.pizzeria.factory.impl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.command.CommandDecorator;
import kz.epam.pizzeria.command.postimpl.*;
import kz.epam.pizzeria.factory.CommandFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.factory.exception.PageNotFoundException;

import java.util.EnumSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.regex.Pattern;

import static kz.epam.pizzeria.entity.enums.Role.ADMIN;
import static kz.epam.pizzeria.entity.enums.Role.ANON;

public class CommandPostFactory implements CommandFactory {

    private CommandPostFactory() {
    }

    private static final CommandPostFactory instance = new CommandPostFactory();

    public static CommandPostFactory getInstance() {
        return instance;
    }

    private static final Logger LOGGER = LogManager.getLogger(CommandPostFactory.class);

    private static Map<String, Command> commandMap = new LinkedHashMap<>();

    static {
        commandMap.put("/login", new CommandDecorator(new Login(), EnumSet.of(ANON)));
        commandMap.put("/admin/create_user", new CommandDecorator(new CreateUser(), EnumSet.of(ADMIN)));
        commandMap.put("/change-language", new ChangeLanguage());
        commandMap.put("/admin/edit-user", new EditAdmin());
        commandMap.put("/admin/block/\\d+", new UserBlock());
        commandMap.put("/admin/unblock/\\d+", new UserUnBlock());
        commandMap.put("/admin/edit-product", new EditProduct());
        commandMap.put("/admin/create-product", new CreateProduct());
        commandMap.put("/admin/create-product-group", new CreateProductGroup());
        commandMap.put("/admin/edit-product-group", new EditProductGroup());
        commandMap.put("/admin/disable-product-group", new DisableProductGroup());
        commandMap.put("/admin/enable-product-group", new EnableProductGroup());
        commandMap.put("/anon/put-item", new PutItemAnon());
        commandMap.put("/anon/minus-item", new MinusItemAnon());
        commandMap.put("/anon/delete-all", new DeleteAllProdFromBasketAnon());
        commandMap.put("/anon/make-order", new MakeOrderAnon());
        commandMap.put("/cancel_order", new CancelOrder());
        commandMap.put("/edit-order", new EditOrder());
        commandMap.put("/operator/plus-product", new PlusProductOperator());
        commandMap.put("/operator/minus-product", new MinusProductOperator());
        commandMap.put("/operator/delete-product", new DeleteProductOperator());
        commandMap.put("/client/put-item", new PutItemClient());
        commandMap.put("/client/minus-item", new MinusItemClient());
        commandMap.put("/client/delete-all", new DeleteAllProdFromBasketClient());
        commandMap.put("/client/make-order", new MakeOrderClient());
        commandMap.put("/registration-begin", new RegistrationBegin());
        commandMap.put("/registration-realization", new RegistrationRealization());
        commandMap.put("/change-self-data", new ChangeSelfData());
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
