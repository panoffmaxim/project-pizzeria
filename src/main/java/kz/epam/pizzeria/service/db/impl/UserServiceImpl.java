package kz.epam.pizzeria.service.db.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.dao.DAOFactory;
import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.dao.mysql.impl.UserDaoImpl;
import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.db.UserService;
import kz.epam.pizzeria.service.exception.IllegalIdException;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public class UserServiceImpl implements UserService {
    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final UserDaoImpl userDaoImpl = dAOFactory.getUserMysqlDao();

    @Override
    public List<User> findAll() throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userDaoImpl.findAll(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<User> findAllByPart(int part) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userDaoImpl.findAllByPart(transaction, (part - 1) * OtherConstants.MAX_PAGINATION_ELEMENTS, OtherConstants.MAX_PAGINATION_ELEMENTS);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findEntityById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            return userDaoImpl.findEntityById(id, transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userDaoImpl.deleteById(id, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean delete(User entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userDaoImpl.delete(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User create(User entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            User user = userDaoImpl.create(entity, transaction);
            if (user == null) {
                transaction.rollBack();
            } else {
                transaction.commit();
            }
            return user;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(User entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = userDaoImpl.update(entity, transaction);
            if (result) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return result;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public User findUserByUsername(String username) throws ServiceException {
        return findAll().stream()
                .filter(u -> u.getUsername().equals(username))
                .findAny()
                .orElse(null);

    }

    @Override
    public void blockById(Integer id) throws ServiceException {
        if (id == null || id <= 0) {
            throw new IllegalIdException("id = " + id);
        }
        try (Transaction transaction = dAOFactory.createTransaction()) {
            User entityById = userDaoImpl.findEntityById(id, transaction);
            if (entityById == null) {
                throw new IllegalIdException("id = " + id);
            }
            if (!entityById.isBlocked()) {
                entityById.setBlocked(true);
                boolean update = userDaoImpl.update(entityById, transaction);
                if (!update) {
                    transaction.rollBack();
                    throw new IllegalIdException("id = " + id);
                } else {
                    transaction.commit();
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public void unBlockById(Integer id) throws ServiceException {
        if (id == null || id < 0) {
            throw new IllegalIdException("id = " + id);
        }
        try (Transaction transaction = dAOFactory.createTransaction()) {
            User entityById = userDaoImpl.findEntityById(id, transaction);
            if (entityById == null) {
                throw new IllegalIdException("id = " + id);
            }
            if (entityById.isBlocked()) {
                entityById.setBlocked(false);
                boolean update = userDaoImpl.update(entityById, transaction);
                if (!update) {
                    transaction.rollBack();
                    throw new IllegalIdException("id = " + id);
                } else {
                    transaction.commit();
                }
            }
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return userDaoImpl.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
