package kz.epam.pizzeria.service.db.impl;

import kz.epam.pizzeria.constant.OtherConstants;
import kz.epam.pizzeria.dao.DAOFactory;
import kz.epam.pizzeria.dao.exception.DaoException;
import kz.epam.pizzeria.dao.mysql.Transaction;
import kz.epam.pizzeria.dao.mysql.impl.ProductGroupMysqlDao;
import kz.epam.pizzeria.dao.mysql.impl.ProductDaoImpl;
import kz.epam.pizzeria.entity.db.impl.Product;
import kz.epam.pizzeria.entity.db.impl.ProductGroup;
import kz.epam.pizzeria.service.db.ProductService;
import kz.epam.pizzeria.service.exception.ServiceException;

import java.util.List;
import java.util.stream.Collectors;

public class ProductServiceImpl implements ProductService {
    private final DAOFactory dAOFactory = DAOFactory.getInstance();
    private final ProductDaoImpl productDaoImpl = dAOFactory.getProductMysqlDao();
    private final ProductGroupMysqlDao productGroupMysqlDao = dAOFactory.getProductGroupMysqlDao();

    @Override
    public List<Product> findAll() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = productDaoImpl.findAll(transaction);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public List<Product> findAllByPart(int part) throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = productDaoImpl.findAllByPart(transaction,
                    (part - 1) * OtherConstants.MAX_PAGINATION_ELEMENTS,
                    OtherConstants.MAX_PAGINATION_ELEMENTS);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    private void buildProduct(Product p, Transaction transaction) {
        ProductGroup productGroup = p.getProductGroup();
        if (productGroup != null) {
            Integer id = productGroup.getId();
            p.setProductGroup(productGroupMysqlDao.findEntityById(id, transaction));
        }
    }

    @Override
    public Product findEntityById(Integer id) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            Product entityById = productDaoImpl.findEntityById(id, transaction);
            buildProduct(entityById, transaction);
            return entityById;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean deleteById(Integer integer) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = productDaoImpl.deleteById(integer, transaction);
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
    public boolean delete(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = productDaoImpl.delete(entity, transaction);
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
    public Product create(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            Product product = productDaoImpl.create(entity, transaction);
            if (product != null) {
                transaction.commit();
            } else {
                transaction.rollBack();
            }
            return product;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public boolean update(Product entity) throws ServiceException {
        try (Transaction transaction = dAOFactory.createTransaction()) {
            boolean result = productDaoImpl.update(entity, transaction);
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
    public List<Product> findAllByProductGroupNull() throws ServiceException {
        return findAll().stream()
                .filter(p -> p.getProductGroup() == null)
                .collect(Collectors.toList());
    }

    @Override
    public List<Product> findAllByProductGroupNotDisabled() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            List<Product> all = productDaoImpl.findAllByProductGroupNotDisabled(transaction);
            all.forEach(p -> buildProduct(p, transaction));
            return all;
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }

    @Override
    public int count() throws ServiceException {
        try (final Transaction transaction = dAOFactory.createTransaction()) {
            return productDaoImpl.count(transaction);
        } catch (DaoException e) {
            throw new ServiceException(e);
        }
    }
}
