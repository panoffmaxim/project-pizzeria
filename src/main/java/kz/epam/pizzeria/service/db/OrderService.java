package kz.epam.pizzeria.service.db;

import kz.epam.pizzeria.entity.db.impl.Order;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public interface OrderService {
    List<Order> findAll() throws ServiceException;

    List<Order> findAllByPart(int part) throws ServiceException;

    Order findEntityById(Integer id) throws ServiceException;

    boolean delete(Order entity) throws ServiceException;

    Order create(Order entity) throws ServiceException;

    boolean update(Order entity) throws ServiceException;

    void plusProduct(Integer orderId, Integer prodId) throws ServiceException;

    void deleteProduct(Integer orderId, Integer prodId) throws ServiceException;

    void minusOrDelete(Integer orderId, Integer prodId) throws ServiceException;

    Order findOrCreateCurrentByUserId(Integer userId) throws ServiceException;

    Order findCurrentByUserId(Integer id) throws ServiceException;

    boolean cancelOrDeleteById(Integer idInt) throws ServiceException;

    int count() throws ServiceException;
}
