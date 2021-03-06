package kz.epam.pizzeria.listener;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

public class SessionListener implements HttpSessionAttributeListener {
    private static final Logger LOGGER = LogManager.getLogger(SessionListener.class);

    public void attributeRemoved(HttpSessionBindingEvent ev) {
        LOGGER.info("attributeRemoved: value = {}, name = {}, id = {}", ev.getValue(), ev.getName(), ev.getSession().getId());
    }

    public void attributeAdded(HttpSessionBindingEvent ev) {
        LOGGER.info("attributeAdded: value = {}, name = {}, id = {}", ev.getValue(), ev.getName(), ev.getSession().getId());
    }

    public void attributeReplaced(HttpSessionBindingEvent ev) {
        LOGGER.debug("attributeReplaced: value = {}, name = {}, id = {}", ev.getValue(), ev.getName(), ev.getSession().getId());
    }
}
