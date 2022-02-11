package kz.epam.pizzeria.controller;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.command.getimpl.PermissionDenied;
import kz.epam.pizzeria.controller.factory.CommandFactory;
import kz.epam.pizzeria.controller.factory.impl.CommandGetFactory;
import kz.epam.pizzeria.controller.factory.impl.CommandPostFactory;
import kz.epam.pizzeria.controller.factory.exception.PageNotFoundException;
import kz.epam.pizzeria.controller.utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "MainServlet")
public class MainServlet extends HttpServlet {
    private static final Logger LOGGER = LogManager.getLogger(MainServlet.class);
    private static final PermissionDenied permDen = new PermissionDenied();
    private final CommandGetFactory commandGetFactory = CommandGetFactory.getInstance();
    private final CommandPostFactory commandPostFactory = CommandPostFactory.getInstance();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            executeMethod(commandGetFactory, request, response);
        } catch (PermissionDeniedException e) {
            LOGGER.debug("Permission denied e:", e);
            ResponseObject execute = permDen.execute(request, response);
            execute.execute(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        executeMethod(commandPostFactory, request, response);
    }

    /**
     * @param commandFactory factory {@link CommandFactory} to create a command {@link Command}
     * @param request        request from user
     * @param response       response to user
     * @throws ServletException          same as throws {@link HttpServlet}
     * @throws IOException               same as throws {@link HttpServlet}
     * @throws PermissionDeniedException if user can't execute specific command {@link Command}
     */

    private void executeMethod(CommandFactory commandFactory,
                               HttpServletRequest request,
                               HttpServletResponse response) throws ServletException, IOException {
        try {
            String requestURI = request.getRequestURI();
            LOGGER.info("requestURI = {}", requestURI);
            String prefix = request.getContextPath() + request.getServletPath();
            LOGGER.info("prefix = {}", prefix);
            Command command = commandFactory.create(requestURI.substring(prefix.length()));
            ResponseObject resp = command.execute(request, response);
            resp.execute(request, response);
        } catch (PageNotFoundException e) {
            LOGGER.debug("e: ", e);
        }
    }
}