package kz.epam.pizzeria.service.db.impl;

import kz.epam.pizzeria.dao.DAOFactory;
import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.dao.mysql.impl.DeliveryInfDaoImpl;
import kz.epam.pizzeria.entity.db.impl.DeliveryInf;
import kz.epam.pizzeria.service.db.DeliveryInfService;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public class DeliveryInfServiceImpl implements DeliveryInfService {
    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private DeliveryInfDaoImpl deliveryInfDaoImpl = dAOFactory.getDeliveryInfMysqlDao();

    @Override
    public List<DeliveryInf> findAll() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return deliveryInfDaoImpl.findAll(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public DeliveryInf findEntityById(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return deliveryInfDaoImpl.findEntityById(id, transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfDaoImpl.deleteById(id, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(DeliveryInf entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfDaoImpl.delete(entity, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public DeliveryInf create(DeliveryInf entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            DeliveryInf deliveryInf = deliveryInfDaoImpl.create(entity, transaction);
            if (deliveryInf == null) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return deliveryInf;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(DeliveryInf entity) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = deliveryInfDaoImpl.update(entity, transaction);
            if (!result) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return result;

        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
