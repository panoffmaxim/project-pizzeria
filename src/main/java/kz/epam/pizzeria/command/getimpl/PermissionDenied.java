package kz.epam.pizzeria.command.getimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.filter.RedirectFilter;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import kz.epam.pizzeria.utils.impl.SendError;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.enums.Role;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class PermissionDenied extends Command {
    private static final Logger LOGGER = LogManager.getLogger(PermissionDenied.class);

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Role role = ((Role) request.getAttribute("role"));
        HttpSession session = request.getSession();
        Map<String, String> redirect = new HashMap<>();
        if (role == Role.ANON) {
            String targetUrl = request.getRequestURI() + "?" + request.getQueryString();
            LOGGER.debug("targetUrl = {}", targetUrl);
            redirect.put("target_url", targetUrl);
            session.setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
            return new Redirect(OtherConstants.LOGIN_PAGE);
        } else {
            return new SendError(OtherConstants.STATUS_CODE_403);
        }
    }
}
