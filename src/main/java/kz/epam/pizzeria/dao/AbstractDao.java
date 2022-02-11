package kz.epam.pizzeria.dao;

import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.entity.db.Entity;

import java.util.List;

public interface AbstractDao<ID, T extends Entity<ID>> {

    List<T> findAll(Transaction transaction);

    List<T> findAllByPart(Transaction transaction, int begin, int count) throws DaoException;

    T findEntityById(ID id, Transaction transaction);

    boolean deleteById(ID id, Transaction transaction);

    boolean delete(T entity, Transaction transaction);

    T create(T entity, Transaction transaction);

    boolean update(T entity, Transaction transaction);

    int count(Transaction transaction) throws DaoException;
}
