package kz.epam.pizzeria.service.db;

import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public interface ProductService {
    List<Product> findAll() throws ServiceException;

    List<Product> findAllByPart(int part) throws ServiceException;

    Product findEntityById(Integer id) throws ServiceException;

    boolean deleteById(Integer id) throws ServiceException;

    boolean delete(Product entity) throws ServiceException;

    Product create(Product entity) throws ServiceException;

    boolean update(Product entity) throws ServiceException;

    List<Product> findAllByProductGroupNotDisabled() throws ServiceException;

    List<Product> findAllByProductGroupNull() throws ServiceException;

    int count() throws ServiceException;
}
