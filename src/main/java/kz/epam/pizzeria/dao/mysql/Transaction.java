package kz.epam.pizzeria.dao.mysql;

import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;

public class Transaction implements AutoCloseable {
    private final ConnectionPool connectionPool = ConnectionPool.getInstance();
    private Connection connection;

    public Transaction() {
        this.connection = connectionPool.takeConnection();
    }

    public void beginTransaction() throws DaoException {
        try {
            connection.setAutoCommit(false);
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        }
    }

    public Connection getConnection() {
        return connection;
    }

    public void commit() throws DaoException {
        try {
            connection.commit();
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        }
    }

    public void rollBack() throws DaoException {
        try {
            connection.rollback();
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        }
    }

    @Override
    public void close() throws DaoException {
        try {
            connection.setAutoCommit(true);
            connectionPool.release(connection);
        } catch (SQLException | NullPointerException e) {
            throw new DaoException(e);
        }
    }
}
