package kz.epam.pizzeria.service.db;

import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public interface DeliveryInfService {
    List<DeliveryInf> findAll() throws ServiceException;

    DeliveryInf findEntityById(Integer id) throws ServiceException;

    boolean deleteById(Integer id) throws ServiceException;

    boolean delete(DeliveryInf entity) throws ServiceException;

    DeliveryInf create(DeliveryInf entity) throws ServiceException;

    boolean update(DeliveryInf entity) throws ServiceException;
}
