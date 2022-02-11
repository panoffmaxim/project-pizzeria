package kz.epam.pizzeria.controller.command.postimpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.controller.command.Command;
import kz.epam.pizzeria.controller.command.PermissionDeniedException;
import kz.epam.pizzeria.controller.utils.ResponseObject;
import kz.epam.pizzeria.controller.utils.impl.Redirect;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.email.MailSender;
import kz.epam.pizzeria.service.encryption.ApplicationEncrypt;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.parser.full.UserParser;
import kz.epam.pizzeria.service.parser.helper.NullIfEmptyService;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.TreeMap;

import static kz.epam.pizzeria.controller.filter.RedirectFilter.REDIRECTED_INFO;

public class RegistrationBegin extends Command {
    private static final Logger LOGGER = LogManager.getLogger(RegistrationRealization.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();
    private final NullIfEmptyService nullEmpt = serviceFactory.getNullIfEmptyService();
    private final UserParser userParser = serviceFactory.getUserParser();
    private final ApplicationEncrypt applicationEncrypt = serviceFactory.getApplicationEncrypt();
    private final MailSender mailSender = serviceFactory.getMailSender();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOGGER.info("execute: begin");
        String referer = request.getHeader("referer");
        TreeMap<String, String> parameters = new TreeMap<>();
        StringBuilder queryString = new StringBuilder();
        Map<String, String> redirect = new HashMap<>();
        User build = validateAndTakeParams(request, redirect, parameters);
        if (build != null) {
            String resultUrl = getUrl(request, queryString, build, parameters);
            Locale locale = request.getLocale();
            boolean result = mailSender.sendRegistration(build.getEmail(), resultUrl, locale, parameters);
            LOGGER.debug("execute: result = {}", result);
            if (result) {
                redirect.put("check_email", "true");
            } else {
                redirect.put("email_send_error", "true");
            }
        }
        request.getSession().setAttribute(REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    private String getUrl(HttpServletRequest request, StringBuilder queryString, User build, TreeMap<String, String> parameters) {
        String token = applicationEncrypt.calcRegistrationToken(build);
        StringBuffer requestURL = request.getRequestURL();
        int length = requestURL.indexOf(request.getContextPath()) + request.getContextPath().length();
        String prefix = requestURL.substring(0, length);
        parameters.put("token", token);
        return prefix + request.getServletPath() + "/registration-realization";
    }

    private User validateAndTakeParams(HttpServletRequest request, Map<String, String> redirect, TreeMap<String, String> parameters) {
        String email = request.getParameter("email");
        parameters.put("email", email);
        String phone = request.getParameter("phone");
        parameters.put("phone", phone);
        String username = request.getParameter("username");
        parameters.put("username", username);
        String password = request.getParameter("password");
        parameters.put("password", password);
        String name = request.getParameter("name");
        parameters.put("name", name);
        String surname = request.getParameter("surname");
        parameters.put("surname", surname);
        String street = request.getParameter("street");
        parameters.put("street", street);
        String house = request.getParameter("house");
        parameters.put("house", house);
        String room = request.getParameter("room");
        parameters.put("room", room);
        String porch = request.getParameter("porch");
        parameters.put("porch", porch);
        String floor = request.getParameter("floor");
        parameters.put("floor", floor);
        return userParser.parseRegistrationUser(redirect, email, phone, username, password, name, surname, street, house, room, porch, floor);
    }
}
