package kz.epam.pizzeria.service.parser.full;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.entity.struct.ValueHolder;
import org.apache.commons.fileupload.FileItem;

import java.util.Map;

public interface ProductGroupParser {
    String NAME = "name";
    String TYPE = "type";
    String DESCRIPTION = "description";
    String PRODUCTS = "products";
    String FILE = "file";
    String ID = "id";
    String DISABLED = "disabled";

    boolean fillFieldsOnCreate(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt);

    boolean fillFieldsOnUpdate(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt);
}
