package kz.epam.pizzeria.filter;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.factory.ServiceFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

public class DenyBlockedUser implements Filter {
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final UserService userService = serviceFactory.getUserService();

    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain) throws IOException, ServletException {
        HttpSession session = req.getSession();
        User user = (User) session.getAttribute("user");
        if (user != null && user.isBlocked()) {
            session.setAttribute("user", null);
            res.sendError(OtherConstants.STATUS_CODE_403);
        } else {
            chain.doFilter(req, res);
        }
    }

    @Override
    public void init(FilterConfig filterConfig) {
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
        if (req instanceof HttpServletRequest && res instanceof HttpServletResponse) {
            this.doFilter((HttpServletRequest) req, (HttpServletResponse) res, chain);
        } else {
            throw new ServletException("non-HTTP request or response");
        }
    }

    @Override
    public void destroy() {
    }

    public UserService getUserService() {
        return userService;
    }
}
