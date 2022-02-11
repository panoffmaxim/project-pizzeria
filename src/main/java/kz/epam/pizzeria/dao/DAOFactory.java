package kz.epam.pizzeria.dao;

import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.dao.mysql.impl.*;

public final class DAOFactory {
    private static final DAOFactory instance = new DAOFactory();
    private final DeliveryInfDaoImpl deliveryInfDaoImpl = new DeliveryInfDaoImpl();
    private final OrderDaoImpl orderDaoImpl = new OrderDaoImpl();
    private final ProductDaoImpl productDaoImpl = new ProductDaoImpl();
    private final ProductGroupMysqlDao productGroupMysqlDao = new ProductGroupMysqlDao();
    private final UserDaoImpl userDaoImpl = new UserDaoImpl();
    private final ImageWriterDao imageWriterDao = new ImageWriterDao();

    private DAOFactory() {
    }

    public static DAOFactory getInstance() {
        return instance;
    }

    public DeliveryInfDaoImpl getDeliveryInfMysqlDao() {
        return deliveryInfDaoImpl;
    }

    public OrderDaoImpl getOrderMysqlDao() {
        return orderDaoImpl;
    }

    public ProductDaoImpl getProductMysqlDao() {
        return productDaoImpl;
    }

    public ProductGroupMysqlDao getProductGroupMysqlDao() {
        return productGroupMysqlDao;
    }

    public UserDaoImpl getUserMysqlDao() {
        return userDaoImpl;
    }

    public ImageWriterDao getImageWriterDao() {
        return imageWriterDao;
    }

    public Transaction createTransaction() throws DaoException {
        Transaction transaction = new Transaction();
        transaction.beginTransaction();
        return transaction;
    }
}
