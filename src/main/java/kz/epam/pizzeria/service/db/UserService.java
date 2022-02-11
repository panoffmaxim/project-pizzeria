package kz.epam.pizzeria.service.db;

import kz.epam.pizzeria.entity.db.impl.User;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;

public interface UserService {
    List<User> findAll() throws ServiceException;

    List<User> findAllByPart(int part) throws ServiceException;

    User findEntityById(Integer id) throws ServiceException;

    boolean deleteById(Integer id) throws ServiceException;

    boolean delete(User entity) throws ServiceException;

    User create(User entity) throws ServiceException;

    boolean update(User entity) throws ServiceException;

    User findUserByUsername(String username) throws ServiceException;

    void blockById(Integer id) throws ServiceException;

    void unBlockById(Integer id) throws ServiceException;

    int count() throws ServiceException;
}
