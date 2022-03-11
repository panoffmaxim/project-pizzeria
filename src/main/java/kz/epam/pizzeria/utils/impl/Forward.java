package kz.epam.pizzeria.utils.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.utils.ResponseObject;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class Forward implements ResponseObject {
    private String path;
    private boolean withPrefix = true;

    public Forward(String path) {
        this.path = path;
    }

    public Forward(String path, boolean withPrefix) {
        this.path = path;
        this.withPrefix = withPrefix;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        if (withPrefix) {
            req.getRequestDispatcher(OtherConstants.JSP_PREFIX + path).forward(req, res);
        } else {
            req.getRequestDispatcher(path).forward(req, res);
        }
    }
}
