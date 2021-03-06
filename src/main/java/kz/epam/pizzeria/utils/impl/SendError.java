package kz.epam.pizzeria.utils.impl;

import kz.epam.pizzeria.utils.ResponseObject;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SendError implements ResponseObject {
    private int code;

    public SendError(int code) {
        this.code = code;
    }

    @Override
    public void execute(HttpServletRequest req, HttpServletResponse res) throws IOException {
        res.sendError(code);
    }
}
