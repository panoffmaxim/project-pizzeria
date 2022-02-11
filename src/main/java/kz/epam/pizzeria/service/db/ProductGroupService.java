package kz.epam.pizzeria.service.db;

import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.entity.enums.ProductType;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public interface ProductGroupService {

    List<ProductGroup> findAll() throws ServiceException;

    List<ProductGroup> findAllByPart(int part) throws ServiceException;

    ProductGroup findEntityById(Integer id) throws ServiceException;

    ProductGroup create(ProductGroup entity) throws ServiceException;

    boolean update(ProductGroup entity) throws ServiceException;

    List<ProductGroup> findAllByProductTypeNotDisabled(ProductType type) throws ServiceException;

    List<ProductGroup> findAllExcept(ProductGroup productGroup) throws ServiceException;

    void disableById(Integer id) throws ServiceException;

    void enableById(Integer id) throws ServiceException;

    int count() throws ServiceException;
}
