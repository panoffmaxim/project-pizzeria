package kz.epam.pizzeria.command.postimpl;

import kz.epam.pizzeria.command.Command;
import kz.epam.pizzeria.filter.RedirectFilter;
import kz.epam.pizzeria.utils.ResponseObject;
import kz.epam.pizzeria.utils.impl.Redirect;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.entity.struct.ValueHolder;
import kz.epam.pizzeria.service.db.ProductGroupService;
import kz.epam.pizzeria.service.exception.ServiceException;
import kz.epam.pizzeria.service.factory.ServiceFactory;
import kz.epam.pizzeria.service.helper.ImageWriterService;
import kz.epam.pizzeria.service.parser.full.ProductGroupParser;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CreateProductGroup extends Command {
    private static final Logger LOGGER = LogManager.getLogger(CreateProductGroup.class);
    private final ServiceFactory serviceFactory = ServiceFactory.getInstance();
    private final ProductGroupService productGroupService = serviceFactory.getProductGroupService();
    private final ImageWriterService imageWriterService = serviceFactory.getImageWriterService();
    private static final DiskFileItemFactory FILE_ITEM_FACTORY = new DiskFileItemFactory();
    private final ProductGroupParser productGroupParser = serviceFactory.getProductGroupParser();

    @Override
    public ResponseObject execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String referer = request.getHeader("referer");
        Map<String, String> redirect = new HashMap<>();
        ValueHolder<String> fileNameHolder = new ValueHolder<>();
        ProductGroup build = parseRequest(request, redirect, fileNameHolder);
        if (build != null) {
            try {
                if (productGroupService.create(build) != null) {
                    return new Redirect("/admin/product-group-list?pagination=1");
                }
            } catch (ServiceException e) {
                LOGGER.debug("Exception due invoking CreateProductGroup ", e);
            }
            redirect.put("unknown_error", "true");
        }
        imageWriterService.deleteImageIfNeed(fileNameHolder.getValue());
        request.getSession().setAttribute(RedirectFilter.REDIRECTED_INFO, redirect);
        return new Redirect(referer, false);
    }

    public ProductGroup parseRequest(HttpServletRequest request, Map<String, String> redirect, ValueHolder<String> holderFileName) {
        try {
            boolean isRight = true;
            ProductGroup productGroup = new ProductGroup();
            ServletFileUpload fileUpload = new ServletFileUpload(FILE_ITEM_FACTORY);
            List<FileItem> parts = fileUpload.parseRequest(request);
            for (FileItem part : parts) {
                isRight = isRight && productGroupParser.fillFieldsOnCreate(productGroup, part, redirect, holderFileName);
            }
            if (!isRight) {
                return null;
            }
            return productGroup;
        } catch (FileUploadException e) {
            LOGGER.error("Error due invoking CreateProductGroup ", e);
            return null;
        }
    }
}
