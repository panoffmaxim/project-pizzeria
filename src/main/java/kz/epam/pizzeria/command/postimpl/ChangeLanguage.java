package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.command.Languages;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.jsp.jstl.core.Config;
import java.io.IOException;
import java.util.Arrays;
import java.util.Optional;

public class ChangeLanguage extends Command {
    private static final Logger LOGGER = LogManager.getLogger(ChangeLanguage.class);

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String lang = request.getParameter("lang");
        Optional<Languages> any = Arrays.stream(Languages.values())
                .filter(l -> l.getKey().equals(lang))
                .findAny();
        if (any.isPresent()) {
            HttpSession session = request.getSession();
            Config.set(session, Config.FMT_LOCALE, any.get().getLocale());
        } else {
            request.setAttribute("locker", true);
        }
        String referer = request.getHeader("referer");
        LOGGER.info("referer = {}", referer);
        return new Redirect(referer, false);
    }
}
