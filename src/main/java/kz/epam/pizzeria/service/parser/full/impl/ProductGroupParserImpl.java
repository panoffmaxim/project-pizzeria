package kz.epam.pizzeria.service.parser.full.impl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.entity.struct.OptionalNullable;
import kz.epam.pizzeria.entity.struct.ValueHolder;
import kz.epam.pizzeria.service.helper.ImageWriterService;
import kz.epam.pizzeria.service.helper.impl.ImageWriterServiceImpl;
import kz.epam.pizzeria.service.parser.helper.ValidateAndPutter;
import kz.epam.pizzeria.service.parser.helper.impl.ValidateAndPutterImpl;
import kz.epam.pizzeria.service.parser.parts.impl.*;
import org.apache.commons.fileupload.FileItem;

import java.io.File;
import java.util.Map;

public class ProductGroupParserImpl implements kz.epam.pizzeria.service.parser.full.ProductGroupParser {
    private static final Logger LOGGER = LogManager.getLogger(ProductGroupParserImpl.class);
    private final ValidateAndPutter validateAndPutter = ValidateAndPutterImpl.getInstance();
    private final ImageWriterService imageWriterService = ImageWriterServiceImpl.getInstance();
    private final ProductGroupNameParser productGroupNameParser = ProductGroupNameParser.getInstance();
    private final ProductTypeParser productTypeParser = ProductTypeParser.getInstance();
    private final ProductGroupDescriptionParser productGroupDescriptionParser = ProductGroupDescriptionParser.getInstance();
    private final IdParser idParser = IdParser.getInstance();
    private final PhotoNameParser photoNameParser = PhotoNameParser.getInstance();
    private final BooleanParser booleanParser = BooleanParser.getInstance();

    @Override
    public boolean fillFieldsOnCreate(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt) {
        File file = null;
        try {
            switch (part.getFieldName()) {
                case NAME:
                    String name = part.getString("UTF-8");
                    OptionalNullable<String> nameOpt = productGroupNameParser.parse(name);
                    if (validateAndPutter.validateAndPut(redirect, nameOpt, NAME, name)) {
                        productGroup.setName(nameOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case TYPE:
                    String type = part.getString("UTF-8");
                    OptionalNullable<ProductType> typeOpt = productTypeParser.parse(type);
                    if (validateAndPutter.validateAndPut(redirect, typeOpt, TYPE, type)) {
                        productGroup.setType(typeOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DESCRIPTION:
                    String description = part.getString("UTF-8");
                    LOGGER.debug("description = {}", description);
                    OptionalNullable<String> descriptionOpt = productGroupDescriptionParser.parse(description);
                    if (validateAndPutter.validateAndPut(redirect, descriptionOpt, DESCRIPTION, description)) {
                        productGroup.setDescription(descriptionOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case PRODUCTS:
                    String product = part.getString("UTF-8");
                    OptionalNullable<Integer> productOpt = idParser.parse(product);
                    if (validateAndPutter.validateAndPut(redirect, productOpt, PRODUCTS, product)) {
                        productGroup.getProducts().add(
                                Product.newBuilder().id(productOpt.get()).build()
                        );
                        return true;
                    } else {
                        return false;
                    }
                case FILE:
                    file = imageWriterService.downloadFile(part);
                    String fileName = file.getName();
                    fileNameOpt.setValue(fileName);
                    OptionalNullable<String> parse = photoNameParser.parse(fileName);
                    if (validateAndPutter.validateAndPut(redirect, parse, FILE, fileName)) {
                        productGroup.setPhotoName(fileName);
                        return true;
                    }
                    return false;
                case ID:
                    String id = part.getString("UTF-8");
                    OptionalNullable<Integer> idOpt = idParser.parse(id);
                    if (validateAndPutter.validateAndPut(redirect, idOpt, ID, id)) {
                        productGroup.setId(idOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DISABLED:
                    String isDisabled = part.getString("UTF-8");
                    OptionalNullable<Boolean> isDisabledOpt = booleanParser.parse(isDisabled);
                    if (validateAndPutter.validateAndPut(redirect, isDisabledOpt, DISABLED, isDisabled)) {
                        productGroup.setDisabled(isDisabledOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                default: {
                    LOGGER.error("irregular field");
                    return true;
                }
            }
        } catch (Exception e) {
            if (file != null) {
                file.delete();
            }
            return false;
        }
    }

    @Override
    public boolean fillFieldsOnUpdate(ProductGroup productGroup, FileItem part, Map<String, String> redirect, ValueHolder<String> fileNameOpt) {
        File file = null;
        try {
            switch (part.getFieldName()) {
                case NAME:
                    String name = part.getString("UTF-8");
                    OptionalNullable<String> nameOpt = productGroupNameParser.parse(name);
                    if (validateAndPutter.validateAndPut(redirect, nameOpt, NAME, name)) {
                        productGroup.setName(nameOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case TYPE:
                    String type = part.getString("UTF-8");
                    OptionalNullable<ProductType> typeOpt = productTypeParser.parse(type);
                    if (validateAndPutter.validateAndPut(redirect, typeOpt, TYPE, type)) {
                        productGroup.setType(typeOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DESCRIPTION:
                    String description = part.getString("UTF-8");
                    LOGGER.debug("description = {}", description);
                    OptionalNullable<String> descriptionOpt = productGroupDescriptionParser.parse(description);
                    if (validateAndPutter.validateAndPut(redirect, descriptionOpt, DESCRIPTION, description)) {
                        productGroup.setDescription(descriptionOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case PRODUCTS:
                    String product = part.getString("UTF-8");
                    OptionalNullable<Integer> productOpt = idParser.parse(product);
                    if (validateAndPutter.validateAndPut(redirect, productOpt, PRODUCTS, product)) {
                        productGroup.getProducts().add(
                                Product.newBuilder().id(productOpt.get()).build()
                        );
                        return true;
                    } else {
                        return false;
                    }
                case FILE:
                    LOGGER.debug("part.getSize() = {}", part.getSize());
                    if (part.getSize() != 0) {
                        file = imageWriterService.downloadFile(part);
                        String fileName = file.getName();
                        fileNameOpt.setValue(fileName);
                        OptionalNullable<String> parse = photoNameParser.parse(fileName);
                        if (validateAndPutter.validateAndPut(redirect, parse, FILE, fileName)) {
                            productGroup.setPhotoName(fileName);
                            return true;
                        }
                        return false;
                    } else {
                        OptionalNullable<String> parse = OptionalNullable.of(null);
                        return validateAndPutter.validateAndPut(redirect, parse, FILE, null);
                    }
                case ID:
                    String id = part.getString("UTF-8");
                    OptionalNullable<Integer> idOpt = idParser.parse(id);
                    if (validateAndPutter.validateAndPut(redirect, idOpt, ID, id)) {
                        productGroup.setId(idOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                case DISABLED:
                    String isDisabled = part.getString("UTF-8");
                    OptionalNullable<Boolean> isDisabledOpt = booleanParser.parse(isDisabled);
                    if (validateAndPutter.validateAndPut(redirect, isDisabledOpt, DISABLED, isDisabled)) {
                        productGroup.setDisabled(isDisabledOpt.get());
                        return true;
                    } else {
                        return false;
                    }
                default: {
                    LOGGER.error("irregular field");
                    return true;
                }
            }
        } catch (Exception e) {
            if (file != null) {
                file.delete();
            }
            return false;
        }
    }
}
