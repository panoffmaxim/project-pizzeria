package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.controller.utils.impl.SendError;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.IllegalPathParamException;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.helper.PathVarCalculator;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class UserBlock extends Command {
    private static final Logger LOGGER = LogManager.getLogger(UserBlock.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final PathVarCalculator pathVarCalculator = serviceFactory.getPathVarCalculator();
    private final UserService userService = serviceFactory.getUserService();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            LOGGER.debug("UserBlockCommand begin");
            Integer id = pathVarCalculator.findLastInteger(request.getPathInfo());
            LOGGER.info("id = {}", id);
            userService.blockById(id);
            LOGGER.debug("block executed");
            return new Redirect("/admin/user-list?pagination=1");
        } catch (ServiceException e) {
            LOGGER.error("e: ", e);
            return new SendError(500);
        }
    }
}
