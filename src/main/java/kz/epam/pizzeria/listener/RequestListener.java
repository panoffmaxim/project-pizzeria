package kz.epam.pizzeria.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.http.HttpServletRequest;

public class RequestListener implements ServletRequestListener {
    private static final Logger LOGGER = LogManager.getLogger(RequestListener.class);

    public void requestInitialized(ServletRequestEvent ev) {
        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
        LOGGER.debug("requestInitialized: uri = {}, req.getRequestedSessionId() = {}", req.getRequestURI(), req.getRequestedSessionId());
    }

    public void requestDestroyed(ServletRequestEvent ev) {
        HttpServletRequest req = (HttpServletRequest) ev.getServletRequest();
        LOGGER.debug("requestDestroyed: uri = {}, req.getRequestedSessionId() = {}", req.getRequestURI(), req.getRequestedSessionId());
    }
}